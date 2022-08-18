package com.example.TestForms.service.impl;

import com.example.TestForms.dao.SessionDAO;
import com.example.TestForms.model.Option;
import com.example.TestForms.model.Question;
import com.example.TestForms.model.Session;
import com.example.TestForms.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class SessionServiceImpl implements SessionService {

    private final SessionDAO sessionDAO;

    @Autowired
    public SessionServiceImpl(SessionDAO sessionDAO) {
        this.sessionDAO = sessionDAO;
    }

    @Override
    @Transactional
    public List<Session> findAll() {
        return sessionDAO.findAll();
    }

    @Override
    @Transactional
    public Session findById(Integer id) {
        return sessionDAO.findById(id);
    }

    @Override
    @Transactional
    public List<Session> findByUserId(Integer id) {
        return sessionDAO.findByUserId(id);
    }

    @Override
    @Transactional
    public List<Session> findByTestId(Integer id) {
        return sessionDAO.findByTestId(id);
    }

    @Override
    @Transactional
    public List<Session> findByDateRange(LocalDate startDate, LocalDate endDate) {
        return sessionDAO.findByDateRange(startDate, endDate);
    }

    @Override
    @Transactional
    public void add(Session session) {
        sessionDAO.add(session);
    }

    @Override
    @Transactional
    public void update(Session session) {
        sessionDAO.update(session);
    }

    @Override
    public Double calcScore(Session session) {
        double score = 0;
        List<Double> points = new ArrayList<>();
        for (Question question : session.getTest().getQuestions()) {
            points.add(session.getChoices().stream()
                    .filter(o -> o.getQuestion().getId() == question.getId())
                    .mapToDouble(Option::getValue)
                    .sum());
        }
        score = points.stream()
                .filter(s -> s > 0)
                .reduce(0D, Double::sum);
        return Math.round(score * 100) / 100D;
    }

    @Override
    public List<String> getSpentTime(List<Session> sessions) {
        List<String> times = new ArrayList<>();
        for (Session session : sessions) {
            long duration = Duration.between(session.getStartTime(), session.getEndTime()).toMillis();
            Long min = TimeUnit.MILLISECONDS.toMinutes(duration);
            Long sec = TimeUnit.MILLISECONDS.toSeconds(duration) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration));
            times.add(String.format("%d min %d sec", min, sec));
        }
        return times;
    }
}
