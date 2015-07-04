package pl.garciapl.banknow.dao.impl;

import org.springframework.transaction.annotation.Transactional;
import pl.garciapl.banknow.dao.TransactionDAO;
import pl.garciapl.banknow.model.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by lukasz on 04.07.15.
 */
@Transactional
public class TransactionDAOImpl implements TransactionDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void storeTransaction(Transaction transaction) {
        entityManager.persist(transaction);
    }

    @Override
    public List<Transaction> getTransactions() {
        return null;
    }
}
