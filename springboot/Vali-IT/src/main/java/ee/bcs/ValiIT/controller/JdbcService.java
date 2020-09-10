package ee.bcs.ValiIT.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class JdbcService {
    @Autowired
    JdbcRepository jdbcRepository;

    //get balance
    public BigDecimal balance(String accNr) {
        BigDecimal balance = jdbcRepository.balance(accNr);
        return balance;
    }

    //make deposit
    public void deposit(String toAccount, BigDecimal amount) {
        BigDecimal currentBalance = jdbcRepository.balance(toAccount); //get balance first
        BigDecimal newBalance = currentBalance.add(amount); //calculate new balance after deposit
        jdbcRepository.updateBalance(toAccount, newBalance); //update account balance
        long accID = jdbcRepository.getAccountID(toAccount); //get accountID
        jdbcRepository.transactionDeposit(amount, accID); //insert transaction to transactions table
    }

    //make withdrawal
    public void withdraw(String fromAccount, BigDecimal amount) {
        BigDecimal currentBalance = jdbcRepository.balance(fromAccount); //get balance first
        if (currentBalance.compareTo(amount) > 0) { //check if balance is sufficient for withdrawal amount
            BigDecimal newBalance = jdbcRepository.balance(fromAccount).subtract(amount); //calculate new balance after withdrawal
            jdbcRepository.updateBalance(fromAccount, newBalance); //update account balance
            long accID = jdbcRepository.getAccountID(fromAccount); //get accountID
            jdbcRepository.transactionWithdrawal(amount, accID); //insert transaction to transactions table
        }
    }


    public void create(Account newAccount) { //create new account
        jdbcRepository.createAccount(newAccount);
    }

    public long createClient(Client newClient) { //create new client
        return jdbcRepository.createClient(newClient);
    }

    //make bank transfer between accounts
    public void transfer(String accountFrom, String accountTo, BigDecimal amount) {
        BigDecimal balanceFrom = jdbcRepository.balance(accountFrom);//get balance of payer
        if (balanceFrom.compareTo(amount) > 0) { //check if balance of payer is sufficient
            withdraw(accountFrom, amount); //execute withdraw function
            deposit(accountTo, amount); //execute deposit function
        }
    }

    //return transactions table
    public List allTransactions(long accID) {
        return jdbcRepository.allTransactions(accID);
    }
}
