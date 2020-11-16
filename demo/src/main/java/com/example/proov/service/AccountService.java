package com.example.proov.service;

import com.example.proov.classesWithFields.Accounts;
import com.example.proov.erandid.ApplicationException;
import com.example.proov.repo.AccountRepository;
import com.example.proov.repo.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

//Exceptions: kontol pole raha (withdraw, transfer); kontot pole olemas (pmst kõik)

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private HistoryRepository historyRepository;

    public void createAccount(String accountNr,
                              int requestCustomerId) {
        List accountList = accountRepository.getAccountColumn();
        if (accountList.contains(accountNr)) {
            throw new ApplicationException("Sellise numbriga konto on olemas.");
        }else{
            accountRepository.createAccount(accountNr, requestCustomerId);
        }
//        int c = 0;
//        for (int i = 0; i < accountList.size(); i++) {
//            if (accountList.get(i).equals(accountNr)){
//                c++;
//                break;
//            }
//        }
//        if (c > 0){
//            throw new ApplicationException("Sellise numbriga konto on olemas.");
//        }else{
//            accountRepository.createAccount(accountNr, requestCustomerId);
//        }
    }



    public BigDecimal getBalance(String requestNr) {
        if (accountRepository.getAccountColumn().contains(requestNr)) {
            return accountRepository.getBalance(requestNr);
        } else {
            throw new ApplicationException("Seda kontot pole olemas");
        }
    }

    public void depositMoneySQL(String accountNr,
                                BigDecimal amount) {

        List accountList = accountRepository.getAccountColumn();
        if (accountList.contains(accountNr)) {
            BigDecimal newBalance = accountRepository.getBalance(accountNr).add(amount);
            accountRepository.updatedBalance(accountNr, newBalance);
            historyRepository.createLog(accountRepository.getId(accountNr), accountNr, amount, newBalance);
        }else {
            throw new ApplicationException("Seda kontot pole olemas");
        }
//        int c = 0;
//        for (int i = 0; i < accountList.size(); i++) {
//            if (accountList.get(i).equals(accountNr)) {
//                BigDecimal newBalance = accountRepository.getBalance(accountNr).add(amount);
//                accountRepository.updatedBalance(accountNr, newBalance);
//                historyRepository.createLog(accountRepository.getId(accountNr), accountNr, amount, newBalance);
//                c++;
//                break;
//            }
//        }
//        if (c == 0) {
//            throw new ApplicationException("Seda kontot pole olemas");
//        }
        //LÜHEM
//
    }

    public void withdrawMoneySQL(String accountNr,
                                 BigDecimal amount) {
        if (accountRepository.getAccountColumn().contains(accountNr)) {
            if (getBalance(accountNr).compareTo(amount) < 0) {
                throw new ApplicationException("Kontol pole piisavalt raha!");
            } else {
                BigDecimal newBalance = accountRepository.getBalance(accountNr).subtract(amount);
                accountRepository.updatedBalance(accountNr, newBalance);
                //BigDecimal newAmount = amount.multiply(createNeg());
                historyRepository.createLog(accountRepository.getId(accountNr), accountNr, amount.multiply(BigDecimal.valueOf(-1)), newBalance);
            }
        } else {
            throw new ApplicationException("Seda kontot pole olemas");
        }
    }

    public void transferMoneySQL(String from,
                                 String to,
                                 BigDecimal amount) {
        if (accountRepository.getAccountColumn().contains(from) && accountRepository.getAccountColumn().contains(to)) {
            if (getBalance(from).compareTo(amount) < 0) {
                throw new ApplicationException("Kontol pole piisavalt raha!");
            } else {
                BigDecimal fromBalance = accountRepository.getBalance(from).subtract(amount);
                BigDecimal toBalance = accountRepository.getBalance(to).add(amount);
                accountRepository.updatedBalance(from, fromBalance);
                accountRepository.updatedBalance(to, toBalance);
                historyRepository.createLog(accountRepository.getId(to), to, amount, toBalance);
                historyRepository.createLog(accountRepository.getId(from), from, amount.multiply(BigDecimal.valueOf(-1)), fromBalance);
            }
        } else {
            throw new ApplicationException("Üht või mõlemat kontot pole olemas");
        }
    }

    public List<Accounts> selectmultipleacc() {
        return accountRepository.selectmultipleacc();
    }

//    public void getId()
}
