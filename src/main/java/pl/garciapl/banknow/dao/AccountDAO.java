package pl.garciapl.banknow.dao;

import pl.garciapl.banknow.model.Account;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by lukasz on 04.07.15.
 */
public interface AccountDAO {

    void createAccount(Account account);

    void updateAccount(Account account);

    List<Account> getAllAccounts();

    Account getAccountById(BigInteger account);
}
