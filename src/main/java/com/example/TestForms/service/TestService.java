package com.example.TestForms.service;

import com.example.TestForms.model.Test;
import com.example.TestForms.model.User;

import java.util.List;

public interface TestService {
    List<Test> findAll();
    List<Test> findActive();
    Test findById(Integer id);
    void save(Test test);
    void update(Test test);
    void delete(Test test);
    void createTest(Test test, List<String> questions, List<String> questionIds, List<String> options, List<String> answers, User user);
}
