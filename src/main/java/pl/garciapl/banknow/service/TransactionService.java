package pl.garciapl.banknow.service;

import pl.garciapl.banknow.model.Transaction;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

/**
 * Created by lukasz on 04.07.15.
 */
public interface TransactionService {

    void storeTransaction(Transaction transaction);

    List<Transaction> getAllTransactions();

    void makeDeposit(BigInteger account, BigDecimal amount);

    void makeTransfer(BigInteger sender, BigInteger recipient, BigDecimal amount);
}
