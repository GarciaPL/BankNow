package pl.garciapl.banknow.dao;

import java.util.List;
import pl.garciapl.banknow.model.Transaction;

/**
 * TransactionDAO - interface for TransactionDAOImpl
 *
 * @author lukasz
 */
public interface TransactionDAO {

    void storeTransaction(Transaction transaction);

    List<Transaction> getAllTransactions();
}
