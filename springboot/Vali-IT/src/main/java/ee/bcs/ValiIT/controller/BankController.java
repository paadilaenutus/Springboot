package ee.bcs.ValiIT.controller;

import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class BankController {
    //new list of accounts
    private static final HashMap<String, BigInteger> accNrs = new HashMap<>();

    //create new account
    @PostMapping("bank")
    public void creat(@RequestBody Bank create) {
        accNrs.put(create.getAccNr(), create.getBalance());
    }

    //deposit money
    @PutMapping("bank/{id}")
    public void deposit(@RequestBody Bank depositMoney, @PathVariable("id") BigInteger a) {
        a = depositMoney.getBalance().add(a);
        accNrs.put(depositMoney.getAccNr(), a);
    }

    //get all accounts & balances
    @GetMapping("bank")
    public HashMap<String, BigInteger> getAll() {
        return accNrs;
    }

    //get balance by account index
    @GetMapping("bank/{id}")
    public BigInteger balance(@PathVariable("id") String a) {

        return accNrs.get(a);
    }
}