package com.example.proov.service;

import com.example.proov.classesWithFields.Accounts;
import com.example.proov.classesWithFields.History;
import com.example.proov.erandid.ApplicationException;
import com.example.proov.repo.AccountRepository;
import com.example.proov.repo.CustomerRepository;
import com.example.proov.repo.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
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
    @Autowired
    private CustomerRepository customerRepository;

    public void createAccount(Accounts accounts) {
        try {
            customerRepository.selectCustomer(accounts.getCustomerId());
//        } catch (DataIntegrityViolationException e) {
//            System.out.print("There is no customer with that id");
        } catch (EmptyResultDataAccessException e){
            throw new ApplicationException("There is no customer with that id");
        }
        List accountList = accountRepository.getAccountColumn();
        if (accountList.contains(accounts.getAccountNr())) {
            throw new ApplicationException("That account number already exists");
        } else {
            accountRepository.createAccount(accounts);
        }
//        else if (idList.contains(requestCustomerId)) {
//            accountRepository.createAccount(accountNr, requestCustomerId);
//        }
    }

    public void depositMoneySQL(String accountNr,
                                BigDecimal amount) {
        List accountList = accountRepository.getAccountColumn();
        if (accountList.contains(accountNr)) {
            accountRepository.updatedBalance(accountNr,
                    accountRepository.getBalance(accountNr).add(amount));
            historyRepository.createLog(accountRepository.getId(accountNr), accountNr,
                    amount, "deposit",
                    accountRepository.getBalance(accountNr).add(amount));
        } else {
            throw new ApplicationException("Seda kontot pole olemas");
        }
        //PIKEM
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
    }

    public void depositMoneyId(int id,
                               BigDecimal amount) {
        accountRepository.updatedBalance(accountRepository.getAccount(id).getAccountNr(),
                accountRepository.getAccount(id).getBalance().add(amount));
        historyRepository.createLog(accountRepository.getId(accountRepository.getAccount(id).getAccountNr()),
                accountRepository.getAccount(id).getAccountNr(), amount, "deposit",
                accountRepository.getAccount(id).getBalance().add(amount));
    }

    public void withdrawMoneySQL(String accountNr,
                                 BigDecimal amount) {
        if (accountRepository.getAccountColumn().contains(accountNr)) {
            if (accountRepository.getBalance(accountNr).compareTo(amount) < 0) {
                throw new ApplicationException("Kontol pole piisavalt raha!");
            } else {
                accountRepository.updatedBalance(accountNr,
                        accountRepository.getBalance(accountNr).subtract(amount));
                historyRepository.createLog(accountRepository.getId(accountNr), accountNr,
                        amount.multiply(BigDecimal.valueOf(-1)), "withdraw",
                        accountRepository.getBalance(accountNr).subtract(amount));
            }
        } else {
            throw new ApplicationException("Seda kontot pole olemas");
        }
    }

    public void transferMoneySQL(String from, String to, BigDecimal amount) {
        if (accountRepository.getAccountColumn().contains(from)
                && accountRepository.getAccountColumn().contains(to)) {
            if (accountRepository.getBalance(from).compareTo(amount) < 0) {
                throw new ApplicationException("Kontol pole piisavalt raha!");
            } else {
                accountRepository.updatedBalance(from, accountRepository.getBalance(from).subtract(amount));
                accountRepository.updatedBalance(to, accountRepository.getBalance(to).add(amount));
                historyRepository.createTransferLog(accountRepository.getId(from), from,
                        amount.multiply(BigDecimal.valueOf(-1)), "transfer",
                        accountRepository.getBalance(from).subtract(amount), to);
                historyRepository.createTransferLog(accountRepository.getId(to), to,
                        amount, "transfer",
                        accountRepository.getBalance(to).add(amount), from);
            }
        } else {
            throw new ApplicationException("Üht või mõlemat kontot pole olemas");
        }
    }

    public List<History> getHistory(int id){
        return historyRepository.getHistory(id);
    }

//    public List<Accounts> selectmultipleacc() {
//        return accountRepository.selectmultipleacc();
//    }
}
