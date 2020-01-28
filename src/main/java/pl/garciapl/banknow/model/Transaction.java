package pl.garciapl.banknow.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.NoArgsConstructor;

/**
 * Transaction
 *
 * @author lukasz
 */
@Entity(name = "Transaction")
@NoArgsConstructor
public class Transaction implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "sender")
    private BigInteger sender;

    @Column(name = "recipient", nullable = false)
    private BigInteger recipient;

    @Column(name = "amount")
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private TransactionType type;

    public Transaction(BigInteger sender, BigInteger recipient, BigDecimal amount, TransactionType type) {
        this.sender = sender;
        this.recipient = recipient;
        this.amount = amount;
        this.type = type;
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

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType transactionType) {
        this.type = transactionType;
    }

}
