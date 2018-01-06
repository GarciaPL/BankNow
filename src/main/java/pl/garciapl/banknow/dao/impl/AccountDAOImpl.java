package pl.garciapl.banknow.dao.impl;

import java.math.BigInteger;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;
import pl.garciapl.banknow.dao.AccountDAO;
import pl.garciapl.banknow.model.Account;

/**
 * AccountDAOImpl - provides data access for account purposes
 *
 * @author lukasz
 */
@Transactional
public class AccountDAOImpl implements AccountDAO {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Persists account model
     *
     * @param account Account
     */
    @Override
    public void createAccount(Account account) {
        entityManager.persist(account);
    }

    /**
     * Updates account model
     *
     * @param account Account
     */
    @Override
    public void updateAccount(Account account) {
        entityManager.merge(account);
    }

    /**
     * Fetches all accounts
     *
     * @return List of Accounts
     */
    @Override
    public List<Account> getAllAccounts() {
        return entityManager.createQuery("Select a FROM Account a", Account.class).getResultList();
    }

    /**
     * Fetches account for particular iban
     *
     * @param iban IBAN
     * @return Account or null
     */
    @Override
    public Account getAccountByIban(BigInteger iban) {
        List<Account> results = entityManager.createQuery("Select a FROM Account a where a.iban = ?1", Account.class).setParameter(1, iban)
                .getResultList();
        if (results == null || results.isEmpty()) {
            return null;
        } else {
            return results.get(0);
        }
    }

    /**
     * Fetches account with particular name and surname
     *
     * @param name Name
     * @param surname Surname
     * @return Account or null
     */
    @Override
    public Account getAccountByNameSurname(String name, String surname) {
        List<Account> results = entityManager
                .createQuery("Select a FROM Account a where a.name like ?1 and a.surname like ?2", Account.class).
                        setParameter(1, name).setParameter(2, surname).getResultList();
        if (results == null || results.isEmpty()) {
            return null;
        } else {
            return results.get(0);
        }
    }
}
