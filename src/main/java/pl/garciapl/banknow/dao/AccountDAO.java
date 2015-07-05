package pl.garciapl.banknow.dao;

import pl.garciapl.banknow.model.Account;

import java.math.BigInteger;
import java.util.List;

/**
 * AccountDAO - interface for AccountDAOImpl
 * @author lukasz
 */
public interface AccountDAO {

    void createAccount(Account account);

    void updateAccount(Account account);

    List<Account> getAllAccounts();

    Account getAccountByIban(BigInteger iban);

    Account getAccountByNameSurname(String name, String surname);
}
