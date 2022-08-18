package com.example.TestForms.dao;

import com.example.TestForms.model.Test;

import java.util.List;

public interface TestDAO {
    List<Test> findAll();
    List<Test> findActive();
    Test findById(Integer id);
    void save(Test test);
    void update(Test test);
    void delete(Test test);
}
