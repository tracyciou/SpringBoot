package com.example.demo.dao;

public interface StudentDao {

    Student getById(Integer studentId);
    // 也可以使用 findById(), queryById()


}