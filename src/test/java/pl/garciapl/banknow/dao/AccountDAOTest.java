package pl.garciapl.banknow.dao;

import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import pl.garciapl.banknow.model.Account;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
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

    @Test
    public void emptyAtStartTest() {
        List<Account> results = entityManager.createQuery("from Account").getResultList();
        Assert.assertTrue(results.isEmpty());
    }

    @Test
    public void persistObjectTest() {
        Assert.assertEquals(0, entityManager.createQuery("from Account").getResultList().size());
        Account account = new Account("john", "malkovich", "Ireland", 123, new BigDecimal(25), "EUR");
        entityManager.persist(account);
        entityManager.flush();
        Assert.assertEquals(1, entityManager.createQuery("from Account").getResultList().size());
    }
}
