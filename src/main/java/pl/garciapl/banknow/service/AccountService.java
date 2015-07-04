package pl.garciapl.banknow.service;

import pl.garciapl.banknow.model.Account;

import java.util.List;

/**
 * Created by lukasz on 04.07.15.
 */
public interface AccountService {

    void createAccount(Account account);

    List<Account> getAllAccounts();
}
