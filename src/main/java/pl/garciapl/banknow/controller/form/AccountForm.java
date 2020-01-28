package pl.garciapl.banknow.controller.form;

import java.math.BigDecimal;
import java.math.BigInteger;
import lombok.Getter;
import lombok.Setter;

/**
 * AccountForm - model for form of account
 *
 * @author lukasz
 */
@Getter
@Setter
public class AccountForm {

    private String name;
    private String surname;
    private String address;
    private BigInteger phone;
    private BigInteger iban;
    private BigDecimal balance;
    private String currency;

}
