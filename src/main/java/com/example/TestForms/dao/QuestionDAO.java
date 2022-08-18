package com.example.TestForms.dao;

import com.example.TestForms.model.Question;

import java.util.List;
import java.util.Set;

public interface QuestionDAO {
    List<Question> findAll();
    Question findById(Integer id);
    void save(Question question);
    void save(Set<Question> questions);
    void update(Question question);
}
