package pl.garciapl.banknow.controller.form;

import java.math.BigDecimal;
import java.math.BigInteger;
import lombok.Getter;
import lombok.Setter;

/**
 * TransferForm
 *
 * @author lukasz
 */
@Getter
@Setter
public class TransferForm {

    private BigInteger sender;
    private BigInteger recipient;
    private BigDecimal amount;


}
