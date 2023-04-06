package com.example.demo.dao;

import com.example.demo.Student;
import com.example.demo.StudentRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class StudentDaoImpl implements StudentDao {

    @Autowired
    // 如果想要使用 NamedParameterJdbcTemplate 裡的方法的話，就會需要先注入 NamedParameterJdbcTemplate 這個 bean 進來
    // 因此需要加上 @Autowired 的註解，這樣子就可以把 NamedParameterJdbcTemplate 的 bean 給注入進來了
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Student getById(Integer studentId) {
        String sql = "SELECT id, name FROM student WHERE id = :studentId";

        Map<String, Object> map = new HashMap<>();
        map.put("studentId", studentId);

        List<Student> list = namedParameterJdbcTemplate.query(sql, map, new StudentRowMapper());
        // query() 方法皆是返回 List

        if (list.size() > 0) {
            return list.get(0);
            // 因為只要返回一個結果，而非 List
            // 所以 .get(0) 去取得第一個(唯一) 的結果
        } else {
            return null;
        }
    }
}