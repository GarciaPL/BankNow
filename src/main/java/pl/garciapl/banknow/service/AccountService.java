package pl.garciapl.banknow.service;

import java.util.List;
import pl.garciapl.banknow.model.Account;
import pl.garciapl.banknow.service.exceptions.AccountExistsException;

/**
 * AccountService - interface for AccountServiceImpl
 *
 * @author lukasz
 */
public interface AccountService {

    void createAccount(Account account) throws AccountExistsException;

    List<Account> getAllAccounts();

}
