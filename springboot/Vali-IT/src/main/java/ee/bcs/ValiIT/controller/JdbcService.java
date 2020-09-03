package ee.bcs.ValiIT.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class JdbcService {
    @Autowired
    JdbcRepository jdbcRepository;

    public BigDecimal balance(String accNr) {
        BigDecimal balance = jdbcRepository.balance(accNr);
        return balance;
    }


    public void deposit(String toAccount, BigDecimal amount) {
        BigDecimal currentBalance = jdbcRepository.balance(toAccount);
        BigDecimal newBalance = currentBalance.add(amount);
        jdbcRepository.updateBalance(toAccount, newBalance);
    }

    public void withdraw(String fromAccount, BigDecimal amount) {
        BigDecimal currentBalance = jdbcRepository.balance(fromAccount);
        if (currentBalance.compareTo(amount) > 0) {
            BigDecimal newBalance = jdbcRepository.balance(fromAccount).subtract(amount);
            jdbcRepository.updateBalance(fromAccount, newBalance);
        }
    }


    public void create(Account newAccount) {
        jdbcRepository.create(newAccount);
    }

    public void transfer(String accountFrom, String accountTo, BigDecimal amount) {
        BigDecimal balanceFrom = jdbcRepository.balance(accountFrom);
        if (balanceFrom.compareTo(amount) > 0) {
            withdraw(accountFrom, amount);
            deposit(accountTo, amount);
        }
    }
}
