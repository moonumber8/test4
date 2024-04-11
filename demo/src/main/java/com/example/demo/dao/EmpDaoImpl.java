package com.example.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Repository
public class EmpDaoImpl implements EmpDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public List getDataEmp() {

        List<Object> params = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        String certCmd = " SELECT EmpName FROM Employee ";

        sb.append("SELECT obj.* FROM (");
        sb.append(certCmd);
        sb.append(") obj ");


        List ret = jdbcTemplate.query(sb.toString(), new PreparedStatementSetter() {
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                if (params.size() > 0) {
                    for (int i = 0; i < params.size(); i++) {
                        if (params.get(i) instanceof String) {
                            preparedStatement.setString(i + 1, (String) params.get(i));
                        } else {
                            preparedStatement.setInt(i + 1, (Integer) params.get(i));
                        }
                    }

                }
            }
        }, new RowMapper<Map<String, String>>() {
            @Override
            public Map<String, String> mapRow(ResultSet rs, int index) throws SQLException {
                Map<String, String> jsonObj = new HashMap<String, String>();
                jsonObj.put("EmpName", rs.getString("EmpName"));
                return jsonObj;
            }
        });
        return ret;
    }
}
