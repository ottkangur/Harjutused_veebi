package com.example.proov.rowmappers;

import com.example.proov.classesWithFields.Accounts;
import com.example.proov.classesWithFields.History;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HistoryRowMapper implements RowMapper<History> {

    @Override
    public History mapRow(ResultSet resultSet, int i) throws SQLException {
        History history = new History();
        history.setAccountNr(resultSet.getString("account_nr"));
        history.setAmount(resultSet.getBigDecimal("amount"));
        history.setBalance(resultSet.getBigDecimal("balance"));
        history.setCustomerId(resultSet.getInt("customer_id"));
        history.setPartneraccountNr(resultSet.getString("partner_account_nr"));
        return history;
    }
}
