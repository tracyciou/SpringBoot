package com.example.demo.service;

import com.example.demo.Student;
import com.example.demo.dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StudentServiceImpl implements StudentService {

    @Autowired
    // 如果想要使用 StudentDao 裡的方法的話，就會需要先注入 StudentDao 這個 bean 進來
    // 因此需要加上 @Autowired 的註解，這樣子就可以把 StudentDao 的 bean 給注入進來了
    private StudentDao studentDao;

    @Override
    public Student getById(Integer studentId) {
        return studentDao.getById(studentId);
    }
}