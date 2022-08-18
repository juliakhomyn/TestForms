package com.example.TestForms.dao.impl;

import com.example.TestForms.dao.RoleDAO;
import com.example.TestForms.model.Role;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleDAOImpl implements RoleDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public RoleDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Role findByTitle(String title) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Role where title=:title");
        query.setParameter("title", title);
        return (Role) query.uniqueResult();
    }

    @Override
    public Role findById(Integer id) {
        return sessionFactory.getCurrentSession().get(Role.class, id);
    }

    @Override
    public List<Role> findAll() {
        return sessionFactory.getCurrentSession().createQuery("from Role").list();
    }
}
