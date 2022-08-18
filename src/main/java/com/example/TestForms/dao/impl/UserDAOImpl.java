package com.example.TestForms.dao.impl;

import com.example.TestForms.dao.UserDAO;
import com.example.TestForms.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public UserDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<User> findAll() {
        return sessionFactory.getCurrentSession().createQuery("from User order by id").list();
    }

    @Override
    public User findById(Integer id) {
        return sessionFactory.getCurrentSession().get(User.class, id);
    }

    @Override
    public User findByEmail(String email) {
        Query query = sessionFactory.getCurrentSession().createQuery("from User where email=:email");
        query.setParameter("email", email);
        User user = (User) query.uniqueResult();
        return user;
    }

    @Override
    public void save(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public void update(User user) {
        sessionFactory.getCurrentSession().update(user);
    }

    @Override
    public void delete(User user) {
        sessionFactory.getCurrentSession().delete(user);
    }
}
