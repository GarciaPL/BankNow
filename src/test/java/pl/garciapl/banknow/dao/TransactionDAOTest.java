package pl.garciapl.banknow.dao;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import pl.garciapl.banknow.model.Transaction;
import pl.garciapl.banknow.model.TransactionType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

/**
 * TransactionDAOTest
 * @author lukasz
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:banknow-db-ctx.xml")
@Transactional
public class TransactionDAOTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private TransactionDAO transactionDao;

    private Transaction transaction;

    @Before
    public void setup() {
        transaction = new Transaction(new BigInteger("123"), new BigInteger("234"), new BigDecimal(25), TransactionType.DEPOSIT);
    }

    @Test
    public void emptyAtStartTest() {
        List<Transaction> results = entityManager.createQuery("from Transaction ").getResultList();
        Assert.assertTrue(results.isEmpty());
    }

    @Test
    public void persistObjectTest() {
        Assert.assertEquals(0, entityManager.createQuery("from Transaction").getResultList().size());
        entityManager.persist(transaction);
        entityManager.flush();
        Assert.assertEquals(1, entityManager.createQuery("from Transaction").getResultList().size());
    }

    @Test
    public void createTransactionTest() {
        transactionDao.storeTransaction(transaction);
        Assert.assertEquals(1, entityManager.createQuery("from Transaction").getResultList().size());
        Assert.assertEquals(this.transaction.getSender(), entityManager.createQuery("from Transaction", Transaction.class).getSingleResult().getSender());
        Assert.assertEquals(this.transaction.getRecipient(), entityManager.createQuery("from Transaction", Transaction.class).getSingleResult().getRecipient());
    }

    @Test
    public void getAllTransactionsTest() {
        entityManager.persist(transaction);
        entityManager.flush();
        List<Transaction> allTransactions = transactionDao.getAllTransactions();
        Assert.assertEquals(1, allTransactions.size());
    }
}
