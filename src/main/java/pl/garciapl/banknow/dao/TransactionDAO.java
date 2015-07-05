package pl.garciapl.banknow.dao;

import pl.garciapl.banknow.model.Transaction;

import java.util.List;

/**
 * TransactionDAO - interface for TransactionDAOImpl
 * @author lukasz
 */
public interface TransactionDAO {

    void storeTransaction(Transaction transaction);

    List<Transaction> getAllTransactions();
}
