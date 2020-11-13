package com.example.proov.repo;

import com.example.proov.rowmappers.JointRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class JointRepository {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List jointTables(){
        String sql = "select * from customer c join account a on c.id = a.customer_id";
        Map<String, Object> paramMap = new HashMap<>();
        return namedParameterJdbcTemplate.query(sql, paramMap, new JointRowMapper());
    }
}
