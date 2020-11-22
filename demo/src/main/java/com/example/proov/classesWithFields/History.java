package com.example.proov.classesWithFields;

import java.math.BigDecimal;
import java.util.List;

public class History {
    int customerId;
    String accountNr;
    BigDecimal amount;
    String typeofaction;
    String partneraccountNr;
    BigDecimal balance;

    public History(int customerId, String accountNr, BigDecimal amount,  String typeofaction, String partneraccountNr, BigDecimal balance) {
        this.customerId = customerId;
        this.accountNr = accountNr;
        this.amount = amount;
        this.typeofaction = typeofaction;
        this.partneraccountNr = partneraccountNr;
        this.balance = balance;
    }

    public History() {
    }

    public String getTypeofaction() {
        return typeofaction;
    }

    public void setTypeofaction(String typeofaction) {
        this.typeofaction = typeofaction;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getAccountNr() {
        return accountNr;
    }

    public void setAccountNr(String accountNr) {
        this.accountNr = accountNr;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getPartneraccountNr() {
        return partneraccountNr;
    }

    public void setPartneraccountNr(String partneraccountNr) {
        this.partneraccountNr = partneraccountNr;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
