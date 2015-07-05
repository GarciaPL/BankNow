package pl.garciapl.banknow.controller.domain;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Created by lukasz on 05.07.15.
 */
public class TransferForm {

    private BigInteger sender;
    private BigInteger recipient;
    private BigDecimal amount;

    public BigInteger getSender() {
        return sender;
    }

    public void setSender(BigInteger sender) {
        this.sender = sender;
    }

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
        return "TransferForm{" +
                "sender='" + sender + '\'' +
                ", recipient='" + recipient + '\'' +
                ", amount='" + amount + '\'' +
                '}';
    }
}
