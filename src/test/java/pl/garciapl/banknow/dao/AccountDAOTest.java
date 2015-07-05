package pl.garciapl.banknow.dao;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import pl.garciapl.banknow.model.Account;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

/**
 * Created by lukasz on 04.07.15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:banknow-db-ctx.xml")
@Transactional
public class AccountDAOTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private AccountDAO accountDao;

    private Account account;

    @Before
    public void setup() {
        account = new Account("john", "malkovich", "Ireland", new BigInteger("500600700"), new BigInteger("1234567890"), new BigDecimal(25), "EUR");
    }

    @Test
    public void emptyAtStartTest() {
        List<Account> results = entityManager.createQuery("from Account").getResultList();
        Assert.assertTrue(results.isEmpty());
    }

    @Test
    public void persistObjectTest() {
        Assert.assertEquals(0, entityManager.createQuery("from Account").getResultList().size());
        entityManager.persist(account);
        entityManager.flush();
        Assert.assertEquals(1, entityManager.createQuery("from Account").getResultList().size());
    }

    @Test
    public void createAccountTest() {
        accountDao.createAccount(account);
        Assert.assertEquals(1, entityManager.createQuery("from Account").getResultList().size());
        Assert.assertEquals(this.account.getName(), entityManager.createQuery("from Account", Account.class).getSingleResult().getName());
        Assert.assertEquals(this.account.getSurname(), entityManager.createQuery("from Account", Account.class).getSingleResult().getSurname());
    }

    @Test
    public void updateAccountTest() {
        account.setName("mark");
        account.setSurname("wahlberg");
        accountDao.updateAccount(account);
        Assert.assertEquals(1, entityManager.createQuery("from Account").getResultList().size());
        Assert.assertEquals("mark", entityManager.createQuery("from Account", Account.class).getSingleResult().getName());
        Assert.assertEquals("wahlberg", entityManager.createQuery("from Account", Account.class).getSingleResult().getSurname());
    }

    @Test
    public void getAllAccountsTest() {
        entityManager.persist(account);
        entityManager.flush();
        List<Account> allAccounts = accountDao.getAllAccounts();
        Assert.assertEquals(1, allAccounts.size());
    }

    @Test
    public void getAccountByIban() {
        entityManager.persist(account);
        entityManager.flush();
        Account accountByIban = accountDao.getAccountByIban(account.getIban());
        Assert.assertEquals(account.getIban(), accountByIban.getIban());
    }

    @Test
    public void getAccountByNameSurname() {
        entityManager.persist(account);
        entityManager.flush();
        Account accountByNameSurname = accountDao.getAccountByNameSurname(account.getName(), account.getSurname());
        Assert.assertEquals(account.getName(), accountByNameSurname.getName());
        Assert.assertEquals(account.getSurname(), accountByNameSurname.getSurname());
    }
}
