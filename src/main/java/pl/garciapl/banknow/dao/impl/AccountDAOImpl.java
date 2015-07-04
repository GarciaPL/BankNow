package pl.garciapl.banknow.dao.impl;

import org.springframework.transaction.annotation.Transactional;
import pl.garciapl.banknow.dao.AccountDAO;
import pl.garciapl.banknow.model.Account;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
    public List<Account> getAllAccounts() {
        return entityManager.createQuery("Select a FROM Account a", Account.class).getResultList();
    }
}
