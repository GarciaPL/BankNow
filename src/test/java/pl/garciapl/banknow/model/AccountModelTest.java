package pl.garciapl.banknow.model;

import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * AccountModelTest
 * @author lukasz
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:banknow-ctx.xml", "classpath:banknow-db-ctx.xml"})
public class AccountModelTest {

    @Test
    public void modelTest() {
        Account account = new Account();

        account.setId(0);
        account.setName("john");
        account.setSurname("malkovich");
        account.setAddress("Ireland");
        account.setPhone(new BigInteger("500600700"));
        account.setIban(new BigInteger("1234567890"));
        account.setBalance(new BigDecimal(25));
        account.setCurrency("EUR");

        Assert.assertEquals(new Integer(0), account.getId());
        Assert.assertEquals("john", account.getName());
        Assert.assertEquals("malkovich", account.getSurname());
        Assert.assertEquals("Ireland", account.getAddress());
        Assert.assertEquals(new BigInteger("500600700"), account.getPhone());
        Assert.assertEquals(new BigInteger("1234567890"), account.getIban());
        Assert.assertEquals(new BigDecimal(25).setScale(2, BigDecimal.ROUND_HALF_UP), account.getBalance());
        Assert.assertEquals("EUR", account.getCurrency());
    }
}
