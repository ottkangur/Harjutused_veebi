package com.example.proov.classesWithFields;

import java.math.BigDecimal;
import java.util.List;

public class Accounts {
    private String accountNr;
    private BigDecimal balance;
    private int customerId;

    public Accounts() {
    }

    public Accounts(String accountNr, BigDecimal balance){
        this.accountNr = accountNr;
        this.balance = BigDecimal.ZERO;
    }

    public String getAccountNr() {
        return accountNr;
    }

    public void setAccountNr(String accountNr) {
        this.accountNr = accountNr;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}
