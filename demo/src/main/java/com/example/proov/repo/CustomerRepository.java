package com.example.proov.repo;

import com.example.proov.classesWithFields.Customer;
import com.example.proov.rowmappers.CustomerRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CustomerRepository {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void createCustomer(String requestName,
                               String requestAddress) {  //need on vaikimisis @RequestParam
        String sql = "INSERT INTO customer (name, address) " +   //'+' tekib ise Enteriga
                "VALUES (:x1, :x2)";                //koolon ütleb, et see on muutuja ja paneb ise jutum'rgid
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("x1", requestName);
        paramMap.put("x2", requestAddress);
        namedParameterJdbcTemplate.update(sql, paramMap);
    }

    public void updateName(int id,
                           String requestName) {
        String sql = "UPDATE customer SET name = :x1 where id = :x3";   //'+' tekib ise Enteriga
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("x1", requestName);
        paramMap.put("x3", id);
        namedParameterJdbcTemplate.update(sql, paramMap);
    }

    public void updateAddress(String requestName,
                              String requestAddress){
        String sql = "update customer set address = :x1 where name = :x2";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("x1", requestAddress);
        paramMap.put("x2", requestName);
        namedParameterJdbcTemplate.update(sql, paramMap);
    }

    public List selectmultiplecus() {
        String sql = "SELECT * FROM customer";
        Map paramMap = new HashMap();
        List<Customer> resultList = namedParameterJdbcTemplate.query(
                sql, paramMap, new CustomerRowMapper());    //CustomerRowMapper tuleb Customer klassi jaoks eraldi teha
        return resultList;
    }

    public Customer selectCustomer(int id){
        String sql = "SELECT * FROM customer WHERE id = :id";   //SQL käsk/süntaks
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", id);
        Customer single = namedParameterJdbcTemplate.queryForObject(
                sql, paramMap, new CustomerRowMapper());
        return single;
    }
}
