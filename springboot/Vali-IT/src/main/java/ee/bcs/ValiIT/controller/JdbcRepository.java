package ee.bcs.ValiIT.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Repository
public class JdbcRepository {
    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    //get balance
    public BigDecimal balance(String accNr) {
        String sql = "select balance from accounts where account_nr = :accnr";
        Map paramMap = new HashMap();
        paramMap.put("accnr", accNr);
        return jdbcTemplate.queryForObject(sql, paramMap, BigDecimal.class);
    }

    //update balance
    public void updateBalance(String account, BigDecimal newBalance) {
        String sql = "UPDATE accounts SET balance = :new where account_nr = :accnr";
        Map paramMap = new HashMap();
        paramMap.put("new", newBalance);
        paramMap.put("accnr", account);
        jdbcTemplate.update(sql, paramMap);
    }

    public void create(Account newAccount) {
        String sql = "INSERT INTO accounts (account_nr, balance, client_id) VALUES (:accnr, :balance, :id)";
        Map paramMap = new HashMap();
        paramMap.put("accnr", newAccount.getAccountNr());
        paramMap.put("balance", newAccount.getBalance());
        paramMap.put("id", newAccount.getClientID());
        jdbcTemplate.update(sql, paramMap);
    }

}
