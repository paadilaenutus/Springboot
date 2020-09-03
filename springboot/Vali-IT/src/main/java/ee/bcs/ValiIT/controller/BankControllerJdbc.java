package ee.bcs.ValiIT.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class BankControllerJdbc {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    //create bank account
    @PostMapping("bnk/create")
    public void createAccount(@RequestBody Account account) {
        Map paramMap = new HashMap();
        String sql = "insert into accounts (account_nr, balance, client_id) values (:accnr, :balance, :clientID)";
        paramMap.put("accnr", account.getAccountNr());
        paramMap.put("balance", account.getBalance());
        paramMap.put("clientID", account.getClientID());
        jdbcTemplate.update(sql, paramMap);
    }

    //deposit sum into account
    @PutMapping("bnk/deposit")
    public void deposit(@RequestBody Account deposit) {
        String sql = "select balance from accounts where account_nr = :accnr";
        Map paramMap = new HashMap();
        paramMap.put("accnr", deposit.getAccountNr());
        BigDecimal balance = jdbcTemplate.queryForObject(sql, paramMap, BigDecimal.class);
        BigDecimal newbal = balance.add(deposit.getBalance());
        String dep = "update accounts set balance = :new where account_nr = :accnr";
        paramMap.put("new", newbal);
        jdbcTemplate.update(dep, paramMap);
    }

    //withdraw from account
    @PutMapping("bnk/withdraw")
    public void withdraw(@RequestBody Account withdraw) {
        String sql = "select balance from accounts where account_nr = :accnr";
        Map paramMap = new HashMap();
        paramMap.put("accnr", withdraw.getAccountNr());
        BigDecimal balance = jdbcTemplate.queryForObject(sql, paramMap, BigDecimal.class);
        BigDecimal newbal = balance.subtract(withdraw.getBalance());
        String wdraw = "update accounts set balance = :new where account_nr = :accnr";
        paramMap.put("new", newbal);
        jdbcTemplate.update(wdraw, paramMap);
    }

    //transfer money between accounts
    @PutMapping("bnk/transfer")
    public void transfer(@RequestBody List<Account> accounts) {
        withdraw(accounts.get(0));
        deposit(accounts.get(1));
    }

    //return all accounts with balance and client_id
    @GetMapping("bnk/accounts")
    public List allAccounts() {
        List<Account> allRows = new ArrayList<>();
        String sql = "SELECT * FROM accounts order by id";
        Map paramMap = new HashMap();
        allRows = jdbcTemplate.query(sql, paramMap, new ObjectRowMapper());
        return allRows;
    }
}
