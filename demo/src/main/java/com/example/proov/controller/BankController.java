package com.example.proov.controller;

import com.example.proov.classesWithFields.Accounts;
import com.example.proov.classesWithFields.Customer;
import com.example.proov.classesWithFields.History;
import com.example.proov.classesWithFields.User;
import com.example.proov.repo.AccountRepository;
import com.example.proov.repo2.CustomerRepository2;
import com.example.proov.service.AccountService;
import com.example.proov.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class BankController {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private CustomerRepository2 customerRepository2;
    @Autowired
    private AccountRepository accountRepository;

    //createCustomer SQL (name, address)
    @PostMapping("customer")
    public List<Customer> createCustomer(
//            @RequestParam("requestName") String requestName,
//                                        @RequestParam("requestAddress") String requestAddress,
                                         @RequestBody Customer customer) {
        customerService.createCustomer(customer);
        return customerService.selectmultiplecus();
    }

    //createAccount SQL (requestNr, requestBalance)
    @PostMapping("account")
    public List<Accounts> createAccount(@RequestBody Accounts accounts
//            @RequestParam("requestNr") String requestNr,
//                              @RequestParam("requestCustomerId") int requestCustomerId
    ) {
        accountService.createAccount(accounts);
        return accountRepository.selectmultipleacc();
    }

    //getBalance (requestNr) SQL
    @CrossOrigin
    @GetMapping("account/balance/{accountNr}")
    public BigDecimal getBalance(@PathVariable("accountNr") String requestNr) {
        return accountRepository.getBalance(requestNr);
    }

    @GetMapping("account/id/{accountNr}")
    public int getId(@PathVariable("accountNr") String accountNr){
        return accountRepository.getId(accountNr);
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
                              @RequestParam("requestAddress") String requestAdress) {
        customerService.updateAddress(requestName, requestAdress);
    }

    //depositMoney (requestNr, requestAmount) SQL
    @PutMapping("account/deposit")
    public BigDecimal depositMoneySQL(@RequestParam("requestNr") String accountNr,
                                @RequestParam("requestAmount") BigDecimal amount) {
        accountService.depositMoneySQL(accountNr, amount);
        return accountRepository.getBalance(accountNr);
    }

//    @PutMapping("account/deposit/id")
//    public void depositMoneyId(int requestId,
//                   BigDecimal requestAmount){
//        accountService.depositMoneyId(requestId, requestAmount);
//    }

    //withrawMoney (requestNr, requestAmount) SQL
    @PutMapping("account/withdraw")
    public BigDecimal withdrawMoneySQL(@RequestParam("requestNr") String accountNr,
                                 @RequestParam("requestAmount") BigDecimal amount) {
        accountService.withdrawMoneySQL(accountNr, amount);
        return accountRepository.getBalance(accountNr);
    }

    //transferMoney (fromAccount, toAccount, requestAmount) SQL
    @PutMapping("account/transfer")
    public BigDecimal transferMoneySQL(@RequestParam("fromNr") String from,
                                 @RequestParam("requestAmount") BigDecimal amount,
                                 @RequestParam("toNr") String to) {
        accountService.transferMoneySQL(from, to, amount);
        return accountRepository.getBalance(from);
    }

    //tagastab kõik account tabeli sisu
//    @GetMapping("selectmultipleaccounts")
//    public List<Accounts> selectmultipleacc() {
//        return accountService.selectmultipleacc();
//    }

//    @GetMapping("register")
//    public String register(String email){
//        System.out.println(email);
//        return "OK";
//    }
//    @PostMapping("register")
//    public String register(){
//        return "OK";
//    }

    @PostMapping("register")
    public List<User> register(@RequestBody User user) {
        System.out.println(user);
        List<User> userList = new ArrayList<>();
        userList.add(user);
        userList.add(new User("first", "smith", 23, "john@com"));
        return userList;
    }

    //tagastab kogu customer tabeli sisu
    @GetMapping("selectmultiplecustomers")
    public List selectmultiplecus() {
        return customerService.selectmultiplecus();
    }

    //tagastab kogu account tabeli sisu
    @GetMapping("accounts/all")
    public List selectmultipleacc(){
        return accountRepository.selectmultipleacc();
    }

    //tagastab ühendatud tabeli
    @GetMapping("jointtables")
    public List getJointTable() {
        return customerService.jointTables();
    }

    //tagastab ühe Customer rea id järgi
    @GetMapping("selectrow")
    public Customer selectCustomerRow(@RequestParam("id") int id) { //Customer näitab, mis id järgi
        return customerService.selectRow(id);
    }

    //tagastab kliendi nime id alusel
    @GetMapping("askName")
    public String askName(@RequestParam("id") int id) {
        return customerService.selectRow(id).getName();
    }

    //returns history of one client by customer id
    @GetMapping("history")
    public History getHistory(@RequestParam("id") int id){
        return accountService.getHistory(id);
    }

//    @GetMapping("askAccount")
//    public List selectAccountRow(@RequestParam("id") int id) {    SELLISE LISTIGA POLE SUURT MIDAGI TEHA
////        accountRepository.getAccount(id);
//        return (List) accountRepository.getAccount(id).get(0);
//    }

    @GetMapping("account/test")
    public String test() {
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
