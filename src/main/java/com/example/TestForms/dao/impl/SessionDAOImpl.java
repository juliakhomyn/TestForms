package com.example.TestForms.dao.impl;

import com.example.TestForms.dao.SessionDAO;
import com.example.TestForms.model.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class SessionDAOImpl implements SessionDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public SessionDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Session> findAll() {
        return sessionFactory.getCurrentSession().createQuery("from Session where end_time is not null order by start_time desc").list();
    }

    @Override
    public Session findById(Integer id) {
        return sessionFactory.getCurrentSession().get(Session.class, id);
    }

    @Override
    public List<Session> findByUserId(Integer id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Session as s where s.user.id=:id and end_time is not null order by start_time desc");
        query.setParameter("id", id);
        return query.list();
    }

    @Override
    public List<Session> findByTestId(Integer id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Session as s where s.test.id=:id and end_time is not null order by start_time desc");
        query.setParameter("id", id);
        return query.list();
    }

    @Override
    public List<Session> findByDateRange(LocalDate startDate, LocalDate endDate) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Session where start_time between :startDate and :endDate and end_time is not null order by start_time desc");
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        return query.list();
    }

    @Override
    public void add(Session session) {
        sessionFactory.getCurrentSession().save(session);
    }

    @Override
    public void update(Session session) {
        sessionFactory.getCurrentSession().update(session);
    }
}
