package com.example.TestForms.dao.impl;

import com.example.TestForms.dao.TestDAO;
import com.example.TestForms.model.Test;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TestDAOImpl implements TestDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public TestDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Test> findAll() {
        return sessionFactory.getCurrentSession().createQuery("from Test").list();
    }

    @Override
    public List<Test> findActive() {
        return sessionFactory.getCurrentSession().createQuery("from Test where is_active = true").list();
    }

    @Override
    public Test findById(Integer id) {
        return sessionFactory.getCurrentSession().get(Test.class, id);
    }

    @Override
    public void save(Test test) {
        sessionFactory.getCurrentSession().save(test);
    }

    @Override
    public void update(Test test) {
        sessionFactory.getCurrentSession().update(test);
    }

    @Override
    public void delete(Test test) {
        sessionFactory.getCurrentSession().delete(test);
    }
}
