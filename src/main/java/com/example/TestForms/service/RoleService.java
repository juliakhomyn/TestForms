package com.example.TestForms.service;

import com.example.TestForms.model.Role;

import java.util.List;

public interface RoleService {
    Role findByTitle(String title);
    Role findById(Integer id);
    List<Role> findAll();
}
