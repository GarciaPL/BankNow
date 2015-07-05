package pl.garciapl.banknow.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Created by lukasz on 04.07.15.
 */
@Entity(name = "Transaction")
public class Transaction implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "sender", nullable = false)
    private BigInteger sender;

    @Column(name = "recipient", nullable = false)
    private BigInteger recipient;

    @Column(name = "amount")
    private BigDecimal amount;

    public Transaction() {
    }

    public Transaction(BigInteger sender, BigInteger recipient, BigDecimal amount) {
        this.sender = sender;
        this.recipient = recipient;
        this.amount = amount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
        return amount.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", sender='" + sender + '\'' +
                ", recipient='" + recipient + '\'' +
                ", amount=" + amount +
                '}';
    }
}
