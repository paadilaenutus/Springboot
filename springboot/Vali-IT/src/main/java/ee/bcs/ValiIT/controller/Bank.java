package ee.bcs.ValiIT.controller;

import java.math.BigInteger;

public class Bank {
    private String accNr;
    private BigInteger balance;

    public String getAccNr() {
        return accNr;
    }

    public void setAccNr(String accNr) {
        this.accNr = accNr;
    }

    public BigInteger getBalance() {
        return balance;
    }

    public void setBalance(BigInteger balance) {
        this.balance = balance;
    }
}
