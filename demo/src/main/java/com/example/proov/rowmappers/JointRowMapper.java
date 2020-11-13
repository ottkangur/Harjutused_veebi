package com.example.proov.rowmappers;

import com.example.proov.Accounts;
import com.example.proov.Joint;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JointRowMapper implements RowMapper<Joint> {
    @Override
    public Joint mapRow(ResultSet resultSet, int i) throws SQLException {
        Joint joint = new Joint();
        joint.setId(resultSet.getLong("id"));
        joint.setName(resultSet.getString("name"));
        joint.setAddress(resultSet.getString("address"));
        joint.setCustomerId(resultSet.getLong("customer_id"));
        joint.setAccountNr(resultSet.getString("account_nr"));
        joint.setBalance(resultSet.getBigDecimal("balance"));
        return joint;
    }
}
