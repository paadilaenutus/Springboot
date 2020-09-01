package ee.bcs.ValiIT.controller;

import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.HashMap;

@RestController
public class BankController {
    //new list of accounts
    private static final HashMap<String, BigInteger> accNrs = new HashMap<>();

    //create new account
    @PostMapping("bank")
    public void creat(@RequestBody Bank create) {
        accNrs.put(create.getAccNr(), create.getBalance());
    }

    //get balance by account index
    @GetMapping("bank/{id}")
    public BigInteger balance(@PathVariable("id") String a) {
        return accNrs.get(a);
    }

    //deposit money
    @PutMapping("bank/deposit/{id}")
    public void deposit(@RequestBody Bank depositMoney,
                        @PathVariable("id") String accnr)
    //@RequestParam(value = "id", required = false) BigInteger temp)
    {
        //s = account number
        accnr = depositMoney.getAccNr();
        //balance = current balance at acc nr
        BigInteger balance = accNrs.get(accnr);
        //add balance to new deposit posted in requestbody
        BigInteger deposit = depositMoney.getBalance().add(balance);
        //update hashmap key/value pair
        accNrs.put(accnr, deposit);
    }

    //withdraw money
    @PutMapping("bank/withdraw/{id}")
    public void withdraw(@RequestBody Bank withdrawMoney,
                         @PathVariable("id") String accnr) {
        accnr = withdrawMoney.getAccNr();
        BigInteger balance = accNrs.get(accnr);
        BigInteger withdraw = balance.subtract(withdrawMoney.getBalance());
        accNrs.put(accnr, withdraw);
    }

    //transfer money between accounts
    @PutMapping("bank/{idsend}/{idreceiver}")
    public void transfer(@RequestBody BigInteger amount, //define amount to transfer in plain json text
                         @PathVariable("idsend") String sender,
                         @PathVariable("idreceiver") String receiver) {
        BigInteger senderBalance = accNrs.get(sender);
        BigInteger receiverBalance = accNrs.get(receiver);
        BigInteger withdraw = senderBalance.subtract(amount);
        BigInteger deposit = receiverBalance.add(amount);
        accNrs.put(sender, withdraw);
        accNrs.put(receiver, deposit);
    }

    //get all accounts & balances
    @GetMapping("bank")
    public HashMap<String, BigInteger> getAll() {
        return accNrs;
    }
}