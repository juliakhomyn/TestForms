package com.example.TestForms.service;

import com.example.TestForms.model.Option;
import com.example.TestForms.model.Session;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public interface OptionService {
    List<Option> findAll();
    Option findById(Integer id);
    void save(Option option);
    void save(Set<Option> options);
    void update(Option option);
    LinkedHashSet<Option> checkAnswers(Session session, HttpServletRequest request);
}
