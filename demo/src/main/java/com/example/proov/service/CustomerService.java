package com.example.proov.service;

import com.example.proov.Customer;
import com.example.proov.repo.CustomerRepository;
import com.example.proov.repo.JointRepository;
import com.example.proov.repo2.CustomerRepository2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerRepository2 customerRepository2;
    @Autowired
    private JointRepository jointRepository;

    //boonus


    public void createCustomer(String requestName,
                               String requestAddress) {//need on vaikimisis @RequestParam
        customerRepository.createCustomer(requestName, requestAddress);
//        String sql = "INSERT INTO customer (name, address) " +   //'+' tekib ise Enteriga
//                "VALUES (:x1, :x2)";                //koolon ütleb, et see on muutuja ja paneb ise jutum'rgid
//        Map<String, String> paramMap = new HashMap<>();
//        paramMap.put("x1", requestName);
//        paramMap.put("x2", requestAddress);
//        namedParameterJdbcTemplate.update(sql, paramMap);
    }

    public void updateName(int id,
                           String requestName) {
        customerRepository.updateName(id, requestName);
    }

    public void updateAddress(String requestName,
                              String requestAddress){
        customerRepository.updateAddress(requestName, requestAddress);
    }

    public List selectmultiplecus() {
        return customerRepository.selectmultiplecus();
    }

    public Customer selectRow(int id){
        return customerRepository.selectRow(id);
    }

    public List jointTables(){
        return jointRepository.jointTables();
    }
}
