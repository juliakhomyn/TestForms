package com.example.TestForms.dao.impl;

import com.example.TestForms.dao.QuestionDAO;
import com.example.TestForms.model.Question;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public class QuestionDAOImpl implements QuestionDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public QuestionDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Question> findAll() {
        return sessionFactory.getCurrentSession().createQuery("from Question").list();
    }

    @Override
    public Question findById(Integer id) {
        return sessionFactory.getCurrentSession().get(Question.class, id);
    }

    @Override
    public void save(Question question) {
        sessionFactory.getCurrentSession().save(question);
    }

    @Override
    public void save(Set<Question> questions) {
        for (Question question : questions) {
            save(question);
        }
    }

    @Override
    public void update(Question question) {
        sessionFactory.getCurrentSession().update(question);
    }
}
