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
import pl.garciapl.banknow.service.exceptions.AccountExistsException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

/**
 * AccountServiceTest
 * @author lukasz
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:banknow-ctx.xml", "classpath:banknow-db-ctx.xml"})
@Transactional
public class AccountServiceTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private AccountService accountService;

    private Account firstAccount;
    private Account secondAccount;

    @Before
    public void setup() {
        firstAccount = new Account("john", "malkovich", "Ireland", new BigInteger("500600700"), new BigInteger("1234567890"), new BigDecimal(25), "EUR");
        secondAccount = new Account("john", "malkovich", "Ireland", new BigInteger("500600700"), new BigInteger("2345678901"), new BigDecimal(25), "EUR");
    }

    @Test
    public void createAccountTest() throws AccountExistsException {
        accountService.createAccount(firstAccount);
        Assert.assertEquals(1, entityManager.createQuery("from Account").getResultList().size());
        Assert.assertEquals(firstAccount.getName(), entityManager.createQuery("from Account", Account.class).getSingleResult().getName());
        Assert.assertEquals(firstAccount.getSurname(), entityManager.createQuery("from Account", Account.class).getSingleResult().getSurname());
    }

    @Test
    public void createAccountExistsIbanTest() {
        try {
            accountService.createAccount(firstAccount);
            accountService.createAccount(firstAccount);
        } catch (AccountExistsException e) {
            Assert.assertEquals("Account already exists with iban : " + firstAccount.getIban().toString(), e.getMessage());
        }
    }

    @Test
    public void createAccountExistsNameSurnameTest() {
        try {
            accountService.createAccount(firstAccount);
            accountService.createAccount(secondAccount);
        } catch (AccountExistsException e) {
            Assert.assertEquals("Account already exists with name : " + firstAccount.getName() + " or surname : " + firstAccount.getSurname() + "", e.getMessage());
        }
    }

    @Test
    public void getAllAccountsTest() {
        entityManager.persist(firstAccount);
        entityManager.flush();
        List<Account> allAccounts = accountService.getAllAccounts();
        Assert.assertEquals(1, allAccounts.size());
    }
}
