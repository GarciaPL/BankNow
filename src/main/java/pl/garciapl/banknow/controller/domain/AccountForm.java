package pl.garciapl.banknow.controller.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import pl.garciapl.banknow.util.MoneySerializer;

import java.math.BigDecimal;

/**
 * Created by lukasz on 04.07.15.
 */
public class AccountForm {

    private String name;
    private String surname;
    private String address;
    private Integer phone;
    private Integer iban;
    @JsonSerialize(using = MoneySerializer.class)
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

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "AccountForm{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", address='" + address + '\'' +
                ", phone=" + phone +
                ", iban=" + iban +
                ", balance=" + balance +
                ", currency='" + currency + '\'' +
                '}';
    }
}
