package pl.garciapl.banknow.dao;

import pl.garciapl.banknow.model.Transaction;

import java.util.List;

/**
 * Created by lukasz on 04.07.15.
 */
public interface TransactionDAO {

    void storeTransaction(Transaction transaction);

    List<Transaction> getTransactions();
}
