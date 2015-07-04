package pl.garciapl.banknow.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import pl.garciapl.banknow.dao.TransactionDAO;
import pl.garciapl.banknow.model.Transaction;
import pl.garciapl.banknow.service.TransactionService;

import java.util.List;

/**
 * Created by lukasz on 04.07.15.
 */
public class TransactionServiceImpl implements TransactionService {

    @Qualifier("transactionDao")
    @Autowired
    private TransactionDAO transactionDao;

    @Override
    public void storeTransaction(Transaction transaction) {
        transactionDao.storeTransaction(transaction);
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionDao.getAllTransactions();
    }
}
