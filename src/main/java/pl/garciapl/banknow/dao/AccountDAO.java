package pl.garciapl.banknow.dao;

import java.math.BigInteger;
import java.util.List;
import pl.garciapl.banknow.model.Account;

/**
 * AccountDAO - interface for AccountDAOImpl
 *
 * @author lukasz
 */
public interface AccountDAO {

    void createAccount(Account account);

    void updateAccount(Account account);

    List<Account> getAllAccounts();

    Account getAccountByIban(BigInteger iban);

    Account getAccountByNameSurname(String name, String surname);
}
