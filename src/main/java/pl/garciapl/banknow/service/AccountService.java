package pl.garciapl.banknow.service;

import pl.garciapl.banknow.model.Account;
import pl.garciapl.banknow.service.exceptions.AccountExistsException;

import java.util.List;

/**
 * Created by lukasz on 04.07.15.
 */
public interface AccountService {

    void createAccount(Account account) throws AccountExistsException;

    List<Account> getAllAccounts();

}
