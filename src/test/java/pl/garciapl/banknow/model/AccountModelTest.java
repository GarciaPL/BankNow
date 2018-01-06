package pl.garciapl.banknow.model;

import static junit.framework.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.BigInteger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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

        assertEquals(new Integer(0), account.getId());
        assertEquals("john", account.getName());
        assertEquals("malkovich", account.getSurname());
        assertEquals("Ireland", account.getAddress());
        assertEquals(new BigInteger("500600700"), account.getPhone());
        assertEquals(new BigInteger("1234567890"), account.getIban());
        assertEquals(new BigDecimal(25).setScale(2, BigDecimal.ROUND_HALF_UP), account.getBalance());
        assertEquals("EUR", account.getCurrency());
    }
}
