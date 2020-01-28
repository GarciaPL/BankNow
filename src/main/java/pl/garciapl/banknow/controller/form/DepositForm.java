package pl.garciapl.banknow.controller.form;

import java.math.BigDecimal;
import java.math.BigInteger;
import lombok.Getter;
import lombok.Setter;

/**
 * DepositForm
 *
 * @author lukasz
 */
@Getter
@Setter
public class DepositForm {

    private BigInteger recipient;
    private BigDecimal amount;

}
