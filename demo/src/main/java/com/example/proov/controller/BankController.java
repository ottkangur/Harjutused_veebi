package com.example.proov.controller;

import com.example.proov.classesWithFields.Accounts;
import com.example.proov.classesWithFields.Customer;
import com.example.proov.repo2.CustomerRepository2;
import com.example.proov.service.AccountService;
import com.example.proov.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class BankController {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private CustomerRepository2 customerRepository2;

    //createCustomer SQL (name, address)
    @PostMapping("customer")
    public void createCustomer(@RequestParam("requestName") String requestName,
                               @RequestParam("requestAddress") String requestAddress){
        customerService.createCustomer(requestName, requestAddress);
    }

    //createAccount SQL (requestNr, requestBalance)
    @PostMapping("account")
    public void createAccount(@RequestParam("requestNr") String requestNr,
                              @RequestParam("requestCustomerId") int requestCustomerId) {
        accountService.createAccount(requestNr, requestCustomerId);
    }


    //getBalance (requestNr) SQL
    @GetMapping("account/{id}")
    public BigDecimal getBalance(@PathVariable("id") String requestNr) {
        return accountService.getBalance(requestNr);
    }

    //update SQL (updateName) SQL
    @PutMapping("customer/{id}")
    public void updateName(@PathVariable("id") int id,
                           @RequestParam("requestName") String requestName) {
        customerService.updateName(id, requestName);
    }

    //aadressi uuendamine
    @PutMapping("customer/address/{name}")
    public void updateAddress(@PathVariable("name") String requestName,
                              @RequestParam("requestAddress") String requestAdress){
        customerService.updateAddress(requestName, requestAdress);
    }

    //depositMoney (requestNr, requestAmount) SQL
    @PutMapping("account/deposit")
    public void depositMoneySQL(@RequestParam("requestNr") String accountNr,
                                @RequestParam("requestAmount") BigDecimal amount) {
        accountService.depositMoneySQL(accountNr, amount);
    }

    //withrawMoney (requestNr, requestAmount) SQL
    @PutMapping("account/withdraw")
    public void withdrawMoneySQL(@RequestParam("requestNr") String accountNr,
                                 @RequestParam("requestAmount") BigDecimal amount) {
        accountService.withdrawMoneySQL(accountNr, amount);
    }

    //transferMoney (fromAccount, toAccount, requestAmount) SQL
    @PutMapping("account/transfer")
    public void transferMoneySQL(@RequestParam("fromNr") String from,
                                 @RequestParam("requestAmount") BigDecimal amount,
                                 @RequestParam("toNr") String to) {
        accountService.transferMoneySQL(from, to, amount);
    }

    //tagastab kõik account tabeli sisu
    @GetMapping("selectmultipleaccounts")
    public List<Accounts> selectmultipleacc() {
        return accountService.selectmultipleacc();
    }

    //tagastab kogu customer tabeli sisu
    @GetMapping("selectmultiplecustomers")
    public List selectmultiplecus() {
        return customerService.selectmultiplecus();
    }

    @GetMapping("jointtables")
    public List getJointTable(){
        return customerService.jointTables();
    }

    //tagastab ühe rea id järgi
    @GetMapping("selectrow")
    public Customer selectRow(@RequestParam("id") int id){ //Customer näitab, mis klassiid järgi
        return customerService.selectRow(id);
    }

    @GetMapping("account/test")
    public String test(){
        return "test";
    }


    //boonus
//    List<Customer> customerList = new ArrayList<>();
//    @GetMapping("test")
//    public List<Customer> getCustomerList(){
//        return customerRepository2.findAll();
//    }

    //-----------||||||||||||||-----------------------------||||||||||||||-----------------------

//    @GetMapping("account")
//    public Map<String, Accounts> accountsTable() {
//        return accounts;
//    }

    //depositMoney (accountNr, money)
//    @PutMapping("account/deposit")
//    public Map<String, Accounts> depositMoney(@RequestParam("nr") String accountNr,
//                                            @RequestParam("money") BigDecimal amount) {
//        //lihtsam
//        //oldValue = accountsBalanceTable.get(accountNr);
//        //BigDecimal newValue = oldValue.add(amount);
//        //accountsBalanceTable.put(accountNr,newValues);
//        //raskem
//        Accounts account = accounts.get(accountNr); //võtan HashMapist ühe konto kontonr alusel
//        BigDecimal oldValue = account.getBalance(); //praeguse väärtuse saamine Getteri abil
//        BigDecimal newValue = oldValue.add(amount); //praegusele väärtusele uue lisamine
//        account.setBalance(newValue);               //balansile uue väärtuse määramine
//        accounts.put(accountNr, account);           //muudetud konto uuendamine HashMapis
//    return accounts;
//    }

//    //getAccountBalance (accountNr)
//    @GetMapping("account/{accountNr}")
//    public BigDecimal balance(@PathVariable("accountNr") String accountNr) {
//        BigDecimal value = accounts.get(accountNr).getBalance();
//        return value;
//    }

    //withrawMoney (accountNr, money)
//    @PutMapping("account/withdraw")
//    public Map<String, Accounts> withdrawMoney(@RequestParam("nr") String accountNr,
//                                               @RequestParam("money")BigDecimal amount) {
//        Accounts account = accounts.get(accountNr); //võtan HashMapist ühe konto kontonr alusel
//        BigDecimal oldValue = account.getBalance(); //praeguse väärtuse saamine Getteri abil
//        BigDecimal newValue = oldValue.subtract(amount); //praegusest väärtusest uue lahutamine
//        account.setBalance(newValue);               //balansile uue väärtuse määramine
//        accounts.put(accountNr, account);           //muudetud konto uuendamine HashMapis
//        return accounts;
//    }

    //transferMoney (fromAccount, toAccount, money)
//    @PutMapping("account/transfer")
//    public Map<String, Accounts> transferMoney(@RequestParam("fromNr") String from,
//                                               @RequestParam("money")BigDecimal amount,
//                                               @RequestParam("toNr") String to) {
//        Accounts accountFrom = accounts.get(from);          //võtan HashMapist ühe konto kontonr alusel
//        BigDecimal oldValueFrom = accountFrom.getBalance(); //praeguse väärtuse saamine Getteri abil
//        BigDecimal newValueFrom = oldValueFrom.subtract(amount); //praegusest väärtusest uue lahutamine
//        accountFrom.setBalance(newValueFrom);                   //balansile uue väärtuse määramine
//        Accounts accountTo = accounts.get(to);              //võtan HashMapist ühe konto kontonr alusel
//        BigDecimal oldValueTo = accountTo.getBalance();     //praeguse väärtuse saamine Getteri abil
//        BigDecimal newValueTo = oldValueTo.add(amount);     //praegusele väärtusele uue lisamine
//        accountTo.setBalance(newValueTo);
//        accounts.put(from, accountFrom);                    //muudetud konto uuendamine HashMapis
//        accounts.put(to, accountTo);
//        return accounts;
//    }
}
