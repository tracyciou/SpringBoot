package com.example.demo.service;

import com.example.demo.Student;
import java.util.List;

public interface StudentService {

    Student getById(Integer studentId);

    List<Student> getAll();
}
