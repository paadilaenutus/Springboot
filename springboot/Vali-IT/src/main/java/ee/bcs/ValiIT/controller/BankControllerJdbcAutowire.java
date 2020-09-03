package ee.bcs.ValiIT.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
public class BankControllerJdbcAutowire {

    @Autowired
    JdbcService jdbcService;

    @PostMapping("create")
    public void create(@RequestBody Account newAccount) {
        jdbcService.create(newAccount);
    }

    @GetMapping("balance")
    public BigDecimal balance(@RequestBody String accNr) {
        BigDecimal balance = jdbcService.balance(accNr);
        return balance;
    }

    @PutMapping("deposit")
    public void depo(@RequestBody MoveMoney depo) {
        jdbcService.deposit(depo.getAccountNr(), depo.getAmount());
    }

    @PutMapping("withdraw")
    public void withd(@RequestBody MoveMoney withdraw) {
        jdbcService.withdraw(withdraw.getAccountNr(), withdraw.getAmount());
    }

    @PutMapping("transfer")
    public void transfer(@RequestBody BankTransfer transfer) {
        jdbcService.transfer(transfer.getAccountFrom(), transfer.getAccountTo(), transfer.getAmount());
    }
}
