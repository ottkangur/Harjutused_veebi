package com.example.proov.service;

import com.example.proov.Accounts;
import com.example.proov.repo.AccountRepository;
import com.example.proov.repo.CustomerRepository;
import com.example.proov.repo.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistoryService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private HistoryRepository historyRepository;

//    public void action(){
//        acc
//    }
}
