package pl.garciapl.banknow.controller.form;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * TransferForm
 *
 * @author lukasz
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

}
