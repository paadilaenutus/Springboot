package ee.bcs.ValiIT.controller;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ObjectRowMapper implements RowMapper<Account> {

    @Override
    public Account mapRow(ResultSet resultSet, int i) throws SQLException {
        Account account = new Account();
        account.setAccountNr(resultSet.getString("account_nr"));
        account.setBalance(resultSet.getBigDecimal("balance"));
        account.setClientID(resultSet.getLong("client_id"));
        return account;
    }
}
