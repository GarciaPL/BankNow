package pl.garciapl.banknow.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import pl.garciapl.banknow.model.Transaction;
import pl.garciapl.banknow.service.exceptions.GenericBankNowException;
import pl.garciapl.banknow.service.exceptions.InsufficientFundsException;

/**
 * TransactionService - interface for TransactionServiceImpl
 *
 * @author lukasz
 */
public interface TransactionService {

    List<Transaction> getAllTransactions();

    void makeDeposit(BigInteger account, BigDecimal amount);

    void makeTransfer(BigInteger sender, BigInteger recipient, BigDecimal amount)
            throws InsufficientFundsException, GenericBankNowException;
}
