package ee.bcs.ValiIT.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    //create new account
    public void createAccount(Account newAccount) {
        String accounts = "INSERT INTO accounts (account_nr, balance, client_id) VALUES (:accnr, :balance, :id)";
        Map paramMap = new HashMap();
        paramMap.put("accnr", newAccount.getAccountNr());
        paramMap.put("balance", newAccount.getBalance());
        paramMap.put("id", newAccount.getClientID());
        jdbcTemplate.update(accounts, paramMap);
    }

    //create new client
    public long createClient(Client newClient) {
        String clients = "INSERT INTO clients (first_name, last_name) values (:first, :last)";
        Map paramMap = new HashMap();
        paramMap.put("first", newClient.getFirstName());
        paramMap.put("last", newClient.getLastName());
        jdbcTemplate.update(clients, paramMap);
        String id = "SELECT id FROM clients WHERE first_name = :first AND last_name = :last";
        return jdbcTemplate.queryForObject(id, paramMap, long.class);
    }

    //get accountID from accountNR
    public long getAccountID(String accNr) {
        Map paramMap = new HashMap();
        String accountID = "SELECT id FROM accounts WHERE account_nr = :accNr";
        paramMap.put("accNr", accNr);
        return jdbcTemplate.queryForObject(accountID, paramMap, long.class);
    }

    //insert deposit into transactions table
    public void transactionDeposit(BigDecimal amount, long accID) {
        Map paramMap = new HashMap();
        String deposit = "INSERT INTO transactions (deposit, account_id) VALUES (:amount, :accID)";
        paramMap.put("amount", amount);
        paramMap.put("accID", accID);
        jdbcTemplate.update(deposit, paramMap);
    }

    //insert withdrawal into transactions table
    public void transactionWithdrawal(BigDecimal amount, long accID) {
        Map paramMap = new HashMap();
        String withdrawal = "INSERT INTO transactions (withdrawal, account_id) VALUES (:amount, :accID)";
        paramMap.put("amount", amount);
        paramMap.put("accID", accID);
        jdbcTemplate.update(withdrawal, paramMap);
    }

    //return transactions table
    public List allTransactions() {
        List<Transaction> allTransactions = new ArrayList<>();
        String sql = "SELECT * FROM transactions ORDER BY trans_id";
        Map paramMap = new HashMap();
        return allTransactions = jdbcTemplate.query(sql, paramMap, new ObjectRowMapper());
    }
}
