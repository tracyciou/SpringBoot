package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class StudentController {

    // 使用 update() 方法：
    @Autowired
    private NamedParameterJdbcTemplate namedparameterjdbctemplate;

    // 新增學生資料
    @PostMapping("/students")
    // Post 對應 create 資料庫
    public String insert(@RequestBody Student student) {
        // 取得放在 request body 的參數
        String sql = "INSERT INTO student(name) VALUE (:studentName)";
        // : 代表是動態的變數，由 map 裡存放的值做決定
        Map<String, Object> map = new HashMap<>();
        map.put("studentName", student.getName());

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedparameterjdbctemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

        int id = keyHolder.getKey().intValue();
        // 把 MySQL 自動生成的 id 的值去存放到 int 類型的 id 變數
        System.out.println("MySQL 自動生成的 id 為: " + id);

        return "執行 INSERT SQL";
    }

    // 新增多筆學生資料
    @PostMapping("/students/batch")
    public String insertList(@RequestBody List<Student> studentList) {
        String sql = "INSERT INTO student(name) VALUE (:studentName)";

        MapSqlParameterSource[] parameterSources = new MapSqlParameterSource[studentList.size()];

        for (int i = 0; i < studentList.size(); i++) {
            Student student = studentList.get(i);

            parameterSources[i] = new MapSqlParameterSource();
            parameterSources[i].addValue("studentName", student.getName());
            // .addValue 是在指定說 SQL 語法裡的變數的值是多少
            // 因為上面 sql 裡寫了一個 studentName 的 sql 變數
            // 所以 addValue 裡的 key 也會是 studentName
            // 那若之後在 sql 變數裡有再加上其他變數的話
            // 在 for loop 裡就必須再去多加一行這個 addValue
            // 再設定那一個 sql 變數的值是多少
            // 所以從概念上來說，用途跟上面的 map 一模一樣
        }

        namedparameterjdbctemplate.batchUpdate(sql, parameterSources);
        return "執行一批 INSERT SQL";
    }

    // 刪除學生資料
    @DeleteMapping("/students/{studentId}")
    public String delete(@PathVariable Integer studentId) {
        // 取得放在 url 路徑中 student id 的值
        String sql = "DELETE FROM student WHERE id = :studentId";
        Map<String, Object> map = new HashMap<>();
        map.put("studentId", studentId);

        namedparameterjdbctemplate.update(sql, map);
        return "執行 DELETE SQL";
    }

    // 查詢學生資料
    @GetMapping("/students")
    public List<Student> select() {
        String sql = "SELECT id, name FROM student";
        Map<String, Object> map = new HashMap<>();

        List<Student> list = namedparameterjdbctemplate.query(sql, map, new StudentRowMapper());
        return list;
    }

    // 查詢學生資料 (加上 WHERE)
    @GetMapping("/students/{studentId}")
    public Student select(@PathVariable Integer studentId) {
        String sql = "SELECT id, name FROM student WHERE id = :studentId";

        Map<String, Object> map = new HashMap<>();
        map.put("studentId", studentId);

        List<Student> list = namedparameterjdbctemplate.query(sql, map, new StudentRowMapper());
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