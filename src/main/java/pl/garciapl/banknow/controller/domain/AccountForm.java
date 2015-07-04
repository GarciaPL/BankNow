package pl.garciapl.banknow.controller.domain;

import java.math.BigDecimal;

/**
 * Created by lukasz on 04.07.15.
 */
public class AccountForm {

    private String name;
    private Integer iban;
    private BigDecimal balance;
    private String currency;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIban() {
        return iban;
    }

    public void setIban(Integer iban) {
        this.iban = iban;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "AccountForm{" +
                "name='" + name + '\'' +
                ", iban=" + iban +
                ", balance=" + balance +
                ", currency='" + currency + '\'' +
                '}';
    }
}
