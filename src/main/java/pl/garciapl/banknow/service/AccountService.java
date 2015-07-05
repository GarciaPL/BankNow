package pl.garciapl.banknow.service;

import pl.garciapl.banknow.model.Account;
import pl.garciapl.banknow.service.exceptions.AccountExistsException;

import java.util.List;

/**
 * AccountService - interface for AccountServiceImpl
 * @author lukasz
 */
public interface AccountService {

    void createAccount(Account account) throws AccountExistsException;

    List<Account> getAllAccounts();

}
