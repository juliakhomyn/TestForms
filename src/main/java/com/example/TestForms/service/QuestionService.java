package com.example.TestForms.service;

import com.example.TestForms.model.Question;

import java.util.List;
import java.util.Set;

public interface QuestionService {
    List<Question> findAll();
    Question findById(Integer id);
    void save(Question question);
    void save(Set<Question> questions);
    void update(Question question);
}
