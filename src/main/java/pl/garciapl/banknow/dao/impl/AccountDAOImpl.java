package pl.garciapl.banknow.dao.impl;

import org.springframework.transaction.annotation.Transactional;
import pl.garciapl.banknow.dao.AccountDAO;
import pl.garciapl.banknow.model.Account;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigInteger;
import java.util.List;

/**
 * Created by lukasz on 04.07.15.
 */
@Transactional
public class AccountDAOImpl implements AccountDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void createAccount(Account account) {
        entityManager.persist(account);
    }

    @Override
    public void updateAccount(Account account) {
        entityManager.merge(account);
    }

    @Override
    public List<Account> getAllAccounts() {
        return entityManager.createQuery("Select a FROM Account a", Account.class).getResultList();
    }

    @Override
    public Account getAccountByIban(BigInteger iban) {
        List<Account> results = entityManager.createQuery("Select a FROM Account a where a.iban = ?1", Account.class).setParameter(1, iban).getResultList();
        if (results == null || results.isEmpty()) {
            return null;
        } else {
            return results.get(0);
        }
    }

    @Override
    public Account getAccountByNameSurname(String name, String surname) {
        List<Account> results = entityManager.createQuery("Select a FROM Account a where a.name like ?1 and a.surname like ?2", Account.class).
                setParameter(1, name).setParameter(2, surname).getResultList();
        if (results == null || results.isEmpty()) {
            return null;
        } else {
            return results.get(0);
        }
    }
}
