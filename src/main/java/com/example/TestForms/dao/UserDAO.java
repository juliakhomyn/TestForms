package com.example.TestForms.dao;


import com.example.TestForms.model.User;

import java.util.List;

public interface UserDAO {
    List<User> findAll();
    User findById(Integer id);
    User findByEmail(String email);
    void save(User user);
    void update(User user);
    void delete(User user);
}
