package pl.garciapl.banknow.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import pl.garciapl.banknow.dao.AccountDAO;
import pl.garciapl.banknow.model.Account;
import pl.garciapl.banknow.service.AccountService;
import pl.garciapl.banknow.service.exceptions.AccountExistsException;

import java.util.List;

/**
 * Created by lukasz on 04.07.15.
 */
public class AccountServiceImpl implements AccountService {

    @Qualifier("accountDao")
    @Autowired
    private AccountDAO accountDao;

    @Override
    public void createAccount(Account account) throws AccountExistsException {
        Account accountByIban = accountDao.getAccountByIban(account.getIban());
        if (accountByIban != null) {
            throw new AccountExistsException("Account already exists with iban : " + account.getIban());
        } else {
            Account accountByNameSurname = accountDao.getAccountByNameSurname(account.getName(), account.getSurname());
            if (accountByNameSurname != null) {
                throw new AccountExistsException("Account already exists with name : " + account.getName() + " or surname : " + account.getSurname());
            } else {
                accountDao.createAccount(account);
            }
        }
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountDao.getAllAccounts();
    }

}
