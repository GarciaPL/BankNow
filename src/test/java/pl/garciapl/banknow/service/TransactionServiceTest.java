package pl.garciapl.banknow.service;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import pl.garciapl.banknow.model.Account;
import pl.garciapl.banknow.model.Transaction;
import pl.garciapl.banknow.model.TransactionType;
import pl.garciapl.banknow.service.exceptions.GenericBankNowException;
import pl.garciapl.banknow.service.exceptions.InsufficientFundsException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

/**
 * TransactionServiceTest
 * @author lukasz
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:banknow-ctx.xml", "classpath:banknow-db-ctx.xml"})
@Transactional
public class TransactionServiceTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private TransactionService transactionService;

    private Account sender;
    private Account recipient;

    private Transaction transaction;

    @Before
    public void setup() {
        transaction = new Transaction(new BigInteger("123"), new BigInteger("234"), new BigDecimal(25), TransactionType.DEPOSIT);
        sender = new Account("john", "malkovich", "Ireland", new BigInteger("500600700"), new BigInteger("1234567890"), new BigDecimal(25), "EUR");
        recipient = new Account("mark", "wahlberg", "Ireland", new BigInteger("700800900"), new BigInteger("234567899"), new BigDecimal(50), "EUR");
    }

    @Test
    public void getAllTransactionsTest() {
        entityManager.persist(transaction);
        entityManager.flush();
        List<Transaction> allTransactions = transactionService.getAllTransactions();
        Assert.assertEquals(1, allTransactions.size());
    }

    @Test
    public void makeDepositTest() {
        entityManager.persist(sender);
        entityManager.flush();
        transactionService.makeDeposit(sender.getIban(), new BigDecimal(100));
        Assert.assertEquals(new BigDecimal(125).setScale(2, BigDecimal.ROUND_HALF_UP), entityManager.createQuery("from Account", Account.class).getSingleResult().getBalance());
    }

    @Test
    public void makeTransferTest() throws InsufficientFundsException, GenericBankNowException {
        entityManager.persist(sender);
        entityManager.flush();
        entityManager.persist(recipient);
        entityManager.flush();
        transactionService.makeTransfer(sender.getIban(), recipient.getIban(), new BigDecimal(5));
        Assert.assertEquals(new BigDecimal(20).setScale(2, BigDecimal.ROUND_HALF_UP), entityManager.createQuery("from Account a where a.iban = ?1", Account.class).
                setParameter(1, sender.getIban()).getSingleResult().getBalance());
        Assert.assertEquals(new BigDecimal(55).setScale(2, BigDecimal.ROUND_HALF_UP), entityManager.createQuery("from Account a where a.iban = ?1", Account.class).
                setParameter(1, recipient.getIban()).getSingleResult().getBalance());
    }

    @Test(expected = GenericBankNowException.class)
    public void makeTransferSameAccountsTest() throws InsufficientFundsException, GenericBankNowException {
        entityManager.persist(sender);
        entityManager.flush();
        transactionService.makeTransfer(sender.getIban(), sender.getIban(), new BigDecimal(5));
    }

    @Test(expected = InsufficientFundsException.class)
    public void makeTransferInsufficientFundsTest() throws InsufficientFundsException, GenericBankNowException {
        entityManager.persist(sender);
        entityManager.flush();
        entityManager.persist(recipient);
        entityManager.flush();
        transactionService.makeTransfer(sender.getIban(), recipient.getIban(), new BigDecimal(500));
    }

}
