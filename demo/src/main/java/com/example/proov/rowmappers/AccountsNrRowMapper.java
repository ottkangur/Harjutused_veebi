package com.example.proov.rowmappers;

import com.example.proov.classesWithFields.Accounts;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountsNrRowMapper implements RowMapper<Accounts> {
    @Override
    public Accounts mapRow(ResultSet resultSet, int i) throws SQLException{
        Accounts account = new Accounts();
        account.setAccountNr(resultSet.getString("account_nr"));
        return account;
    }
}