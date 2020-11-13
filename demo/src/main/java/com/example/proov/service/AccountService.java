package com.example.proov.service;

import com.example.proov.Accounts;
import com.example.proov.erandid.ApplicationException;
import com.example.proov.repo.AccountRepository;
import com.example.proov.repo.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private HistoryRepository historyRepository;

//    public BigDecimal createNeg(){
//    BigDecimal neg = BigDecimal.valueOf(-1);
//        return neg;
//    }

    public void createAccount(String requestNr,
                              int requestCustomerId) {
        accountRepository.createAccount(requestNr, requestCustomerId);
    }

    public BigDecimal getBalance(String requestNr) {
        return accountRepository.getBalance(requestNr);
    }

    public void depositMoneySQL(String accountNr,
                                BigDecimal amount) {
        List accountList = accountRepository.getAccountColumn();
//        for (int i = 0; i < accountList.size(); i++) {
//            System.out.println(accountList.get(i).toString());
//            System.out.println();
        if (accountList.contains(accountNr)) {
            BigDecimal newBalance = accountRepository.getBalance(accountNr).add(amount);
            accountRepository.updatedBalance(accountNr, newBalance);
            historyRepository.createLog(accountRepository.getId(accountNr), accountNr, amount, newBalance);
//                break;
        }else {
            throw new ApplicationException("Seda kontot pole olemas");
        }
    }

    public void withdrawMoneySQL(String accountNr,
                                 BigDecimal amount) {
        if (getBalance(accountNr).compareTo(amount) < 0) {
            throw new ApplicationException("Kontol pole piisavalt raha!");
        } else {
            BigDecimal newBalance = accountRepository.getBalance(accountNr).subtract(amount);
            accountRepository.updatedBalance(accountNr, newBalance);
            //BigDecimal newAmount = amount.multiply(createNeg());
            historyRepository.createLog(accountRepository.getId(accountNr), accountNr, amount.multiply(BigDecimal.valueOf(-1)), newBalance);
        }
    }

    public void transferMoneySQL(String from,
                                 String to,
                                 BigDecimal amount) {
        if (getBalance(from).compareTo(amount) < 0) {
            throw new ApplicationException("Kontol pole piisavalt raha!");
        } else {
            BigDecimal fromBalance = accountRepository.getBalance(from).subtract(amount);
            BigDecimal toBalance = accountRepository.getBalance(to).add(amount);
            accountRepository.updatedBalance(from, fromBalance);
            accountRepository.updatedBalance(to, toBalance);
            historyRepository.createLog(accountRepository.getId(to), to, amount, toBalance);
            //BigDecimal newAmount = amount.multiply(createNeg());
            historyRepository.createLog(accountRepository.getId(from), from, amount.multiply(BigDecimal.valueOf(-1)), fromBalance);
        }
    }


    public List<Accounts> selectmultipleacc() {
        return accountRepository.selectmultipleacc();
    }

//    public void getId()
}
