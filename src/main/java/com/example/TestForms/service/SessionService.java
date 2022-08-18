package com.example.TestForms.service;

import com.example.TestForms.model.Session;

import java.time.LocalDate;
import java.util.List;

public interface SessionService {
    List<Session> findAll();
    Session findById(Integer id);
    List<Session> findByUserId(Integer id);
    List<Session> findByTestId(Integer id);
    List<Session> findByDateRange(LocalDate startDate, LocalDate endDate);
    void add(Session session);
    void update(Session session);
    Double calcScore(Session session);
    List<String> getSpentTime(List<Session> sessions);
}
