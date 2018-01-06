package pl.garciapl.banknow.controller.form;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * AccountForm - model for form of account
 *
 * @author lukasz
 */
public class AccountForm {

    private String name;
    private String surname;
    private String address;
    private BigInteger phone;
    private BigInteger iban;
    private BigDecimal balance;
    private String currency;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigInteger getIban() {
        return iban;
    }

    public void setIban(BigInteger iban) {
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigInteger getPhone() {
        return phone;
    }

    public void setPhone(BigInteger phone) {
        this.phone = phone;
    }

}
