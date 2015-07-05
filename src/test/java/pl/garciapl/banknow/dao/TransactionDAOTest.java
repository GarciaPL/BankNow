package pl.garciapl.banknow.dao;

import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import pl.garciapl.banknow.model.Transaction;

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
public class TransactionDAOTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void emptyAtStartTest() {
        List<Transaction> results = entityManager.createQuery("from Transaction ").getResultList();
        Assert.assertTrue(results.isEmpty());
    }

    @Test
    public void persistObjectTest() {
        Assert.assertEquals(0, entityManager.createQuery("from Transaction").getResultList().size());
        Transaction transaction = new Transaction(new BigInteger("123"), new BigInteger("234"), new BigDecimal(25));
        entityManager.persist(transaction);
        entityManager.flush();
        Assert.assertEquals(1, entityManager.createQuery("from Transaction").getResultList().size());
    }
}
