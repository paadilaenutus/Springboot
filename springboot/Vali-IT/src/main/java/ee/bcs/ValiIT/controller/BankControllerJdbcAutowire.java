package ee.bcs.ValiIT.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
public class BankControllerJdbcAutowire {

    @Autowired
    JdbcService jdbcService;

    //create new account
    @PostMapping("account")
    public void create(@RequestBody Account newAccount) {
        jdbcService.create(newAccount);
    }

    //create new client
    @PostMapping("client")
    public long createClient(@RequestBody Client newClient) {
        return jdbcService.createClient(newClient);
    }

    //get account balance
    @GetMapping("balance/{accnr}")
    public BigDecimal balance(@PathVariable("accnr") String accNr) {
        BigDecimal balance = jdbcService.balance(accNr);
        return balance;
    }

    //make deposit to account
    @PutMapping("deposit")
    public void depo(@RequestBody MoveMoney depo) {
        jdbcService.deposit(depo.getAccountNr(), depo.getAmount());
    }

    //make withdrawal from account
    @PutMapping("withdraw")
    public void withd(@RequestBody MoveMoney withdraw) {
        jdbcService.withdraw(withdraw.getAccountNr(), withdraw.getAmount());
    }

    //make transaction between accounts
    @PutMapping("transfer")
    public void transfer(@RequestBody BankTransfer transfer) {
        jdbcService.transfer(transfer.getAccountFrom(), transfer.getAccountTo(), transfer.getAmount());
    }

    //return full transactions table
    @GetMapping("transactions")
    public List transactions() {
        return jdbcService.allTransactions();
    }
}
