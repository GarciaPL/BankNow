package pl.garciapl.banknow.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import pl.garciapl.banknow.dao.AccountDAO;
import pl.garciapl.banknow.dao.TransactionDAO;
import pl.garciapl.banknow.model.Account;
import pl.garciapl.banknow.model.Transaction;
import pl.garciapl.banknow.model.TransactionType;
import pl.garciapl.banknow.service.TransactionService;
import pl.garciapl.banknow.service.exceptions.GenericBankNowException;
import pl.garciapl.banknow.service.exceptions.InsufficientFundsException;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

/**
 * TransactionServiceImpl - provides business logic for transaction purposes
 * @author lukasz
 */
public class TransactionServiceImpl implements TransactionService {

    @Qualifier("transactionDao")
    @Autowired
    private TransactionDAO transactionDao;

    @Qualifier("accountDao")
    @Autowired
    private AccountDAO accountDao;

    /**
     * Fetches all transactions
     * @return List of transactions
     */
    @Override
    public List<Transaction> getAllTransactions() {
        return transactionDao.getAllTransactions();
    }

    /**
     * Performs transfer of funds between two accounts
     * @param sender Sender IBAN
     * @param recipient Recipient IBAN
     * @param amount Amount of funds
     * @throws InsufficientFundsException
     * @throws GenericBankNowException
     */
    @Override
    public void makeTransfer(BigInteger sender, BigInteger recipient, BigDecimal amount) throws InsufficientFundsException, GenericBankNowException {
        if (sender.compareTo(recipient) == 0) {
            throw new GenericBankNowException("Sender and recipient are the same accounts");
        } else {
            Account accountSender = accountDao.getAccountByIban(sender);
            if (accountSender.getBalance().compareTo(amount) < 0) {
                throw new InsufficientFundsException("Insufficient funds to proceed transfer");
            } else {
                Account accountRecipient = accountDao.getAccountByIban(recipient);
                accountSender.setBalance(accountSender.getBalance().subtract(amount));
                accountRecipient.setBalance(accountRecipient.getBalance().add(amount));

                accountDao.updateAccount(accountSender);
                accountDao.updateAccount(accountRecipient);

                transactionDao.storeTransaction(new Transaction(sender, recipient, amount, TransactionType.TRANSFER));
            }
        }
    }

    /**
     * Performs deposit of funds to one account
     * @param account Recipient IBAN
     * @param amount Amount of funds
     */
    @Override
    public void makeDeposit(BigInteger account, BigDecimal amount) {
        Account accountDeposit = accountDao.getAccountByIban(account);
        accountDeposit.setBalance(accountDeposit.getBalance().add(amount));
        accountDao.updateAccount(accountDeposit);

        transactionDao.storeTransaction(new Transaction(null, account, amount, TransactionType.DEPOSIT));
    }
}
