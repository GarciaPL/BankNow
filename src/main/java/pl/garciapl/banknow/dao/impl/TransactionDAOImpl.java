package pl.garciapl.banknow.dao.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;
import pl.garciapl.banknow.dao.TransactionDAO;
import pl.garciapl.banknow.model.Transaction;

/**
 * TransactionDAOImpl - provides data access for transaction purposes
 *
 * @author lukasz
 */
@Transactional
public class TransactionDAOImpl implements TransactionDAO {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Persists transaction model
     *
     * @param transaction Transaction
     */
    @Override
    public void storeTransaction(Transaction transaction) {
        entityManager.persist(transaction);
    }

    /**
     * Fetches all transactions
     *
     * @return List of transactions
     */
    @Override
    public List<Transaction> getAllTransactions() {
        return entityManager.createQuery("Select t FROM Transaction t", Transaction.class).getResultList();
    }
}
