package pl.garciapl.banknow.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import pl.garciapl.banknow.dao.AccountDAO;
import pl.garciapl.banknow.dao.TransactionDAO;
import pl.garciapl.banknow.model.Account;
import pl.garciapl.banknow.model.Transaction;
import pl.garciapl.banknow.service.TransactionService;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

/**
 * Created by lukasz on 04.07.15.
 */
public class TransactionServiceImpl implements TransactionService {

    @Qualifier("transactionDao")
    @Autowired
    private TransactionDAO transactionDao;

    @Qualifier("accountDao")
    @Autowired
    private AccountDAO accountDao;

    @Override
    public void storeTransaction(Transaction transaction) {
        transactionDao.storeTransaction(transaction);
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionDao.getAllTransactions();
    }

    @Override
    public void makeTransfer(BigInteger sender, BigInteger recipient, BigDecimal amount) {
        if (sender.compareTo(recipient) == 0) {

        } else {
            Account senderAccount = accountDao.getAccountById(sender);
            if (senderAccount.getBalance().compareTo(amount) < 0) {

            } else {

            }
        }

        transactionDao.storeTransaction(new Transaction(sender, recipient, amount));
    }

    @Override
    public void makeDeposit(BigInteger account, BigDecimal amount) {
        Account accountDeposit = accountDao.getAccountById(account);
        accountDeposit.setBalance(accountDeposit.getBalance().add(amount));
        accountDao.updateAccount(accountDeposit);

        transactionDao.storeTransaction(new Transaction(null, account, amount));
    }
}
