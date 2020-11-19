package com.example.proov.repo;

import com.example.proov.classesWithFields.Accounts;
import com.example.proov.classesWithFields.Customer;
import com.example.proov.rowmappers.AccountsRowMapper;
import com.example.proov.rowmappers.CustomerRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AccountRepository {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void createAccount(String accountNr,
                              int requestCustomerId) {
        String sql = "INSERT INTO account (account_nr, balance,  customer_id) " +   //'+' tekib ise Enteriga
                "VALUES (:x1, :x2, :x3)";                //koolon ütleb, et see on muutuja ja paneb ise jutumärgid
        Map<String, Object> paramMap = new HashMap<>();  //kui Mapi 2. kohal on Object, võib erinevaid tüüpi andmeid sisestada...
        paramMap.put("x1", accountNr);                   //...nt x1 vs x2
        paramMap.put("x2", BigDecimal.ZERO);
        paramMap.put("x3", requestCustomerId);
        namedParameterJdbcTemplate.update(sql, paramMap);
    }

    //tagastab listi
    public Accounts getAccount(int id){      //Customer näitab, mis id järgi
        String sql = "SELECT * FROM account WHERE customer_id = :id";   //SQL käsk/süntaks
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", id);
        Accounts oneAccount = namedParameterJdbcTemplate.queryForObject(
                sql, paramMap, new AccountsRowMapper());
        return oneAccount;
    }

    public BigDecimal getBalance(String accountNr) {
        String sql = "SELECT balance FROM account WHERE account_nr = :account_nr"; //SQL käsk/süntaks
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("account_nr", accountNr);
        BigDecimal balance = namedParameterJdbcTemplate.queryForObject(sql, paramMap, BigDecimal.class);
        return balance;
    }

    public void updatedBalance(String accountNr, BigDecimal newBalance){
        String sql2 = "UPDATE account SET balance = :newBalance WHERE account_nr = :account_nr";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("account_nr", accountNr);
        paramMap.put("newBalance", newBalance);
        namedParameterJdbcTemplate.update(sql2, paramMap);
    }

    public List<Accounts> selectmultipleacc() {
        String sql = "SELECT * FROM account";
        Map paramMap = new HashMap();
        List<Accounts> resultList = namedParameterJdbcTemplate.query(
                sql, paramMap, new AccountsRowMapper());    //AccountsRowMapper tuleb Accounts klassi jaoks eraldi teha
        return resultList;
    }

    public int getId(String accountNr){
        String sql = "SELECT customer_id FROM account where account_nr = :account_nr";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("account_nr", accountNr);
        int id = namedParameterJdbcTemplate.queryForObject(sql, paramMap, Integer.class);
        return id;
    }

    public List getAccountColumn(){
        String sql = "SELECT account_nr FROM account";
        Map paramMap = new HashMap();
        List<String> accounts = namedParameterJdbcTemplate.queryForList(sql, paramMap, String.class);
        return accounts;
    }
}
