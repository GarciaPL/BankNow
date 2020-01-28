package pl.garciapl.banknow.dao.impl;

public class AccountSQL {

    private AccountSQL() {
    }

    public static final String SELECT_ACCOUNT = "Select a FROM Account a";
    public static final String SELECT_ACCOUNT_FOR_IBAN = "Select a FROM Account a where a.iban = ?1";
    public static final String SELECT_ACCOUNT_FOR_NAME_AND_SURNAME = "Select a FROM Account a where a.name like ?1 and a.surname like ?2";

}
