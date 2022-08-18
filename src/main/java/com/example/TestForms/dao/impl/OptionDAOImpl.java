package com.example.TestForms.dao.impl;

import com.example.TestForms.dao.OptionDAO;
import com.example.TestForms.model.Option;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public class OptionDAOImpl implements OptionDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public OptionDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Option> findAll() {
        return sessionFactory.getCurrentSession().createQuery("from Option").list();
    }

    @Override
    public Option findById(Integer id) {
        return sessionFactory.getCurrentSession().get(Option.class, id);
    }

    @Override
    public void save(Option option) {
        sessionFactory.getCurrentSession().save(option);
    }

    @Override
    public void save(Set<Option> options) {
        for (Option option : options) {
            save(option);
        }
    }

    @Override
    public void update(Option option) {
        sessionFactory.getCurrentSession().update(option);
    }
}
