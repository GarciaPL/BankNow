package pl.garciapl.banknow.model;

import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * TransactionModelTest
 * @author lukasz
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:banknow-ctx.xml", "classpath:banknow-db-ctx.xml"})
public class TransactionModelTest {

    @Test
    public void transactionTest() {
        Transaction transaction = new Transaction();
        transaction.setId(0);
        transaction.setSender(new BigInteger("123"));
        transaction.setRecipient(new BigInteger("234"));
        transaction.setAmount(new BigDecimal(25));
        transaction.setType(TransactionType.DEPOSIT);
        Assert.assertEquals(new Integer(0), transaction.getId());
        Assert.assertEquals(new BigInteger("123"), transaction.getSender());
        Assert.assertEquals(new BigInteger("234"), transaction.getRecipient());
        Assert.assertEquals(new BigDecimal(25).setScale(2, BigDecimal.ROUND_HALF_UP), transaction.getAmount());
        Assert.assertEquals(TransactionType.DEPOSIT, transaction.getType());
    }
}
