package com.example.TestForms.service.impl;

import com.example.TestForms.dao.QuestionDAO;
import com.example.TestForms.model.Question;
import com.example.TestForms.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionDAO questionDAO;

    @Autowired
    public QuestionServiceImpl(QuestionDAO questionDAO) {
        this.questionDAO = questionDAO;
    }

    @Override
    @Transactional
    public List<Question> findAll() {
        return questionDAO.findAll();
    }

    @Override
    @Transactional
    public Question findById(Integer id) {
        return questionDAO.findById(id);
    }

    @Override
    @Transactional
    public void save(Question question) {
        questionDAO.save(question);
    }

    @Override
    @Transactional
    public void save(Set<Question> questions) {
        for (Question question : questions) {
            save(question);
        }
    }

    @Override
    @Transactional
    public void update(Question question) {
        questionDAO.update(question);
    }
}
