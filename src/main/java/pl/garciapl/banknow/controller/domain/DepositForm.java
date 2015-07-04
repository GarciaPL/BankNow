package pl.garciapl.banknow.controller.domain;

import java.math.BigDecimal;

/**
 * Created by lukasz on 04.07.15.
 */
public class DepositForm {

    private String account;
    private BigDecimal amount;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
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
                "account='" + account + '\'' +
                ", amount=" + amount +
                '}';
    }
}
