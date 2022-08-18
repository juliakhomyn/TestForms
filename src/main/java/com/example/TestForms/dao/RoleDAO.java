package com.example.TestForms.dao;

import com.example.TestForms.model.Role;

import java.util.List;

public interface RoleDAO {
    Role findByTitle(String title);
    Role findById(Integer id);
    List<Role> findAll();
}
