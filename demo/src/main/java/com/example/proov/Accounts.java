package com.example.proov;

import java.math.BigDecimal;

public class Accounts {
    private String accountNr;
    private BigDecimal balance;

    public Accounts(String accountNr){
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
}
