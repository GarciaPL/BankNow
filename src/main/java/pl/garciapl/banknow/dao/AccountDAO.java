package pl.garciapl.banknow.dao;

import pl.garciapl.banknow.model.Account;

import java.util.List;

/**
 * Created by lukasz on 04.07.15.
 */
public interface AccountDAO {

    void createAccount(Account account);

    List<Account> getAccounts();
}
