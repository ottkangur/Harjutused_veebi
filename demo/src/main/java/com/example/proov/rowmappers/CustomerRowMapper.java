package com.example.proov.rowmappers;

import com.example.proov.classesWithFields.Customer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerRowMapper implements RowMapper<Customer>{
        @Override
        public Customer mapRow(ResultSet resultSet, int i) throws SQLException {
            Customer customer = new Customer();
            customer.setName(resultSet.getString("name"));
            customer.setAddress(resultSet.getString("address"));
            return customer;
        }
}
