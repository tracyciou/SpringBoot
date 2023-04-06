package com.example.demo.dao;

import com.example.demo.Student;

public interface StudentDao {
    Student getById(Integer studentId);
    // 也可以使用 findById(), queryById()
}