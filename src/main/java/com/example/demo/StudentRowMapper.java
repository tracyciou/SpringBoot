package com.example.demo;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentRowMapper implements RowMapper<Student> {

    @Override
    public Student mapRow(ResultSet resultSet, int i) throws SQLException {
    // i 代表取到第幾條 row
        Student student = new Student();
        student.setId(resultSet.getInt("id"));
        // 取得 table 中 id column 的數據
        student.setName(resultSet.getString("name"));
        // 取得 table 中 name column 的數據
        // 因為 query() 裡的 SQL 只有 SELECT id 跟 name
        // 所以這裡是 setId 跟 setName

        return student;
    }
}
