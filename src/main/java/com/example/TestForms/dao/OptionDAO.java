package com.example.TestForms.dao;

import com.example.TestForms.model.Option;

import java.util.List;
import java.util.Set;

public interface OptionDAO {
    List<Option> findAll();
    Option findById(Integer id);
    void save(Option option);
    void save(Set<Option> options);
    void update(Option option);
}
