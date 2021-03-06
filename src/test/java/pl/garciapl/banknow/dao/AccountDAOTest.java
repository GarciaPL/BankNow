package pl.garciapl.banknow.dao;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import pl.garciapl.banknow.model.Account;

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
        account = new Account("john", "malkovich", "Ireland", new BigInteger("500600700"), new BigInteger("1234567890"), new BigDecimal(25),
                "EUR");
    }

    @Test
    public void emptyAtStartTest() {
        List<Account> results = entityManager.createQuery("from Account").getResultList();

        assertTrue(results.isEmpty());
    }

    @Test
    public void persistObjectTest() {
        assertEquals(0, entityManager.createQuery("from Account").getResultList().size());
        entityManager.persist(account);
        entityManager.flush();
        assertEquals(1, entityManager.createQuery("from Account").getResultList().size());
    }

    @Test
    public void createAccountTest() {
        accountDao.createAccount(account);

        assertEquals(1, entityManager.createQuery("from Account").getResultList().size());
        assertEquals(this.account.getName(), entityManager.createQuery("from Account", Account.class).getSingleResult().getName());
        assertEquals(this.account.getSurname(),
                entityManager.createQuery("from Account", Account.class).getSingleResult().getSurname());
    }

    @Test
    public void updateAccountTest() {
        account.setName("mark");
        account.setSurname("wahlberg");
        accountDao.updateAccount(account);
        assertEquals(1, entityManager.createQuery("from Account").getResultList().size());
        assertEquals("mark", entityManager.createQuery("from Account", Account.class).getSingleResult().getName());
        assertEquals("wahlberg", entityManager.createQuery("from Account", Account.class).getSingleResult().getSurname());
    }

    @Test
    public void getAllAccountsTest() {
        entityManager.persist(account);
        entityManager.flush();
        List<Account> allAccounts = accountDao.getAllAccounts();

        assertEquals(1, allAccounts.size());
    }

    @Test
    public void getAccountByIban() {
        entityManager.persist(account);
        entityManager.flush();
        Account accountByIban = accountDao.getAccountByIban(account.getIban());

        assertEquals(account.getIban(), accountByIban.getIban());
    }

    @Test
    public void getAccountByIbanReturnsNull() {
        Account accountByIban = accountDao.getAccountByIban(account.getIban());

        assertEquals(null, accountByIban);
    }

    @Test
    public void getAccountByNameSurname() {
        entityManager.persist(account);
        entityManager.flush();
        Account accountByNameSurname = accountDao.getAccountByNameSurname(account.getName(), account.getSurname());

        assertEquals(account.getName(), accountByNameSurname.getName());
        assertEquals(account.getSurname(), accountByNameSurname.getSurname());
    }

    @Test
    public void getAccountByNameSurnameReturnsNull() {
        Account accountByNameSurname = accountDao.getAccountByNameSurname(account.getName(), account.getSurname());

        assertEquals(null, accountByNameSurname);
    }
}
