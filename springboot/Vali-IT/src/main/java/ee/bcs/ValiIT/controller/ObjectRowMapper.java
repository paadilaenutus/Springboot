package ee.bcs.ValiIT.controller;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ObjectRowMapper implements RowMapper<Transaction> {

    @Override
    public Transaction mapRow(ResultSet resultSet, int i) throws SQLException {
        Transaction transaction = new Transaction();
        transaction.setDeposit(resultSet.getBigDecimal("deposit"));
        transaction.setWithdrawal(resultSet.getBigDecimal("withdrawal"));
        transaction.setAccountID(resultSet.getLong("account_id"));
        return transaction;
    }
}
