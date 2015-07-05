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
 * Created by lukasz on 05.07.15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:banknow-ctx.xml", "classpath:banknow-db-ctx.xml"})
@Transactional
public class AccountServiceTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private AccountService accountService;

    private Account account;

    @Before
    public void setup() {
        account = new Account("john", "malkovich", "Ireland", new BigInteger("500600700"), new BigInteger("1234567890"), new BigDecimal(25), "EUR");
    }

    @Test
    public void createAccountTest() throws AccountExistsException {
        accountService.createAccount(account);
        Assert.assertEquals(1, entityManager.createQuery("from Account").getResultList().size());
        Assert.assertEquals(account.getName(), entityManager.createQuery("from Account", Account.class).getSingleResult().getName());
        Assert.assertEquals(account.getSurname(), entityManager.createQuery("from Account", Account.class).getSingleResult().getSurname());
    }

    @Test(expected = AccountExistsException.class)
    public void createAccountExistsExceptionTest() throws AccountExistsException {
        accountService.createAccount(account);
        accountService.createAccount(account);
    }

    @Test
    public void createAccountExistsTest() {
        try {
            accountService.createAccount(account);
            accountService.createAccount(account);
        } catch (AccountExistsException e) {
            Assert.assertEquals("Account already exists with iban : " + account.getIban().toString(), e.getMessage());
        }
    }

    @Test
    public void getAllAccountsTest() {
        entityManager.persist(account);
        entityManager.flush();
        List<Account> allAccounts = accountService.getAllAccounts();
        Assert.assertEquals(1, allAccounts.size());
    }
}
