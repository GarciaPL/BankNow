package pl.garciapl.banknow.controller.domain;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Created by lukasz on 04.07.15.
 */
public class DepositForm {

    private BigInteger recipient;
    private BigDecimal amount;

    public BigInteger getRecipient() {
        return recipient;
    }

    public void setRecipient(BigInteger recipient) {
        this.recipient = recipient;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "DepositForm{" +
                "recipient=" + recipient +
                ", amount=" + amount +
                '}';
    }
}
