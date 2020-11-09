package com.example.proov;

import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class BankController {

//    private long accountNr;
//
//    public long getAccountNr() {
//        return accountNr;
//    }
//
//    public void setAccountNr(long accountNr) {
//        this.accountNr = accountNr;
//    }

    public Map<String, Accounts> accounts = new HashMap<>();
    //public Accounts nimi= new Accounts();

    //createAccount (AccountNr)
    @PostMapping("account")
    //public void createAccount(@RequestBody String accountNr){
    //account.put(accountNr, new Accounts(accountNr);
    public void bank(@RequestBody String accountNr){
        accounts.put(accountNr, new Accounts(accountNr));
        //return Accounts(getAccountNr, getBalance);
    }

    @GetMapping("account")
    public Map<String, Accounts> accountsTable(){
        return accounts;
    }

//    @GetMapping("account/{id}")
//    public Accounts accountsTable(@PathVariable("id") int id) {
//
//        return accounts.get(id);
//    }

    //depositMoney (accountNr, money)
    @PutMapping("account/deposit")
    public Map<String, Accounts> depositMoney(@RequestParam("nr") String accountNr,
                                            @RequestParam("money") BigDecimal amount) {
        //lihtsam
        //oldValue = accountsBalanceTable.get(accountNr);
        //BigDecimal newValue = oldValue.add(amount);
        //accountsBalanceTable.put(accountNr,newValues);
        //raskem
        Accounts account = accounts.get(accountNr); //võtan HashMapist ühe konto kontonr alusel
        BigDecimal oldValue = account.getBalance(); //praeguse väärtuse saamine Getteri abil
        BigDecimal newValue = oldValue.add(amount); //praegusele väärtusele uue lisamine
        account.setBalance(newValue);               //balansile uue väärtuse määramine
        accounts.put(accountNr, account);           //muudetud konto uuendamine HashMapis
    return accounts;
    }

    //getAccountBalance (accountNr)
    @GetMapping("account/{accountNr}")
    public BigDecimal balance(@PathVariable("accountNr") String accountNr){
        BigDecimal value = accounts.get(accountNr).getBalance();
        return value;
    }

    //withrawMoney (accountNr, money)
    @PutMapping("account/withdraw")
    public Map<String, Accounts> withdrawMoney(@RequestParam("nr") String accountNr,
                                               @RequestParam("money")BigDecimal amount) {
        Accounts account = accounts.get(accountNr); //võtan HashMapist ühe konto kontonr alusel
        BigDecimal oldValue = account.getBalance(); //praeguse väärtuse saamine Getteri abil
        BigDecimal newValue = oldValue.subtract(amount); //praegusest väärtusest uue lahutamine
        account.setBalance(newValue);               //balansile uue väärtuse määramine
        accounts.put(accountNr, account);           //muudetud konto uuendamine HashMapis
        return accounts;
    }

    //transferMoney (fromAccount, toAccount, money)
    @PutMapping("account/transfer")
    public Map<String, Accounts> transferMoney(@RequestParam("fromNr") String from,
                                               @RequestParam("money")BigDecimal amount,
                                               @RequestParam("toNr") String to) {
        Accounts accountFrom = accounts.get(from);          //võtan HashMapist ühe konto kontonr alusel
        BigDecimal oldValueFrom = accountFrom.getBalance(); //praeguse väärtuse saamine Getteri abil
        BigDecimal newValueFrom = oldValueFrom.subtract(amount); //praegusest väärtusest uue lahutamine
        accountFrom.setBalance(newValueFrom);                   //balansile uue väärtuse määramine
        Accounts accountTo = accounts.get(to);              //võtan HashMapist ühe konto kontonr alusel
        BigDecimal oldValueTo = accountTo.getBalance();     //praeguse väärtuse saamine Getteri abil
        BigDecimal newValueTo = oldValueTo.add(amount);     //praegusele väärtusele uue lisamine
        accountTo.setBalance(newValueTo);
        accounts.put(from, accountFrom);                    //muudetud konto uuendamine HashMapis
        accounts.put(to, accountTo);
        return accounts;
    }
}
