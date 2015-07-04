package pl.garciapl.banknow.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import pl.garciapl.banknow.dao.AccountDAO;
import pl.garciapl.banknow.model.Account;
import pl.garciapl.banknow.service.AccountService;

import java.util.List;

/**
 * Created by lukasz on 04.07.15.
 */
public class AccountServiceImpl implements AccountService {

    @Qualifier("accountDao")
    @Autowired
    private AccountDAO accountDao;

    @Override
    public void createAccount(Account account) {

    }

    @Override
    public List<Account> getAccounts() {
        return null;
    }
}
