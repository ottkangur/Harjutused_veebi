package com.example.proov.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Repository
public class HistoryRepository {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void createLog(int accountId,
                          String accountNr,
                          BigDecimal amount,
                          BigDecimal balance) {
        String sql = "INSERT INTO history (customer_id, account_nr,  amount, balance)" +   //'+' tekib ise Enteriga
                "VALUES (:x1, :x2, :x3, :x4)";                //koolon ütleb, et see on muutuja ja paneb ise jutumärgid
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("x1", accountId);
        paramMap.put("x2", accountNr);
        paramMap.put("x3", amount);
        paramMap.put("x4", balance);
        namedParameterJdbcTemplate.update(sql, paramMap);
    }

//    public void createLog(int accountId,
//                          String accountNr,
//                          BigDecimal amount,
//                          String transaction,
//                          BigDecimal balance) {
//        String sql = "INSERT INTO history (customer_id, home_account,  amount, transaction, balance)" +   //'+' tekib ise Enteriga
//                "VALUES (:x1, :x2, :x3, :x4, :x5)";                //koolon ütleb, et see on muutuja ja paneb ise jutumärgid
//        Map<String, Object> paramMap = new HashMap<>();
//        paramMap.put("x1", accountId);
//        paramMap.put("x2", accountNr);
//        paramMap.put("x3", amount);
//        paramMap.put("x4", transaction);
//        paramMap.put("x5", balance);
//        namedParameterJdbcTemplate.update(sql, paramMap);
//    }
//
//    public void createLogTransfer(int homeAccountId,
//                                  String homeAccountNr,
//                                  BigDecimal amount,
//                                  String transaction,
//                                  String awayAccount,
//                                  int awayAccountId,
//                                  BigDecimal balance) {
//        String sql = "INSERT INTO history (customer_id, home_account,  amount, transaction, away_account, away_id, balance)" +   //'+' tekib ise Enteriga
//                "VALUES (:x1, :x2, :x3, :x4, :x5, :x6, :x7)";                //koolon ütleb, et see on muutuja ja paneb ise jutumärgid
//        Map<String, Object> paramMap = new HashMap<>();
//        paramMap.put("x1", homeAccountId);
//        paramMap.put("x2", homeAccountNr);
//        paramMap.put("x3", amount);
//        paramMap.put("x4", transaction);
//        paramMap.put("x5", awayAccount);
//        paramMap.put("x6", awayAccountId);
//        paramMap.put("x7", balance);
//        namedParameterJdbcTemplate.update(sql, paramMap);
//    }

}
