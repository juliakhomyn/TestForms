package com.example.TestForms.service.impl;

import com.example.TestForms.dao.RoleDAO;
import com.example.TestForms.model.Role;
import com.example.TestForms.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleDAO roleDAO;

    @Autowired
    public RoleServiceImpl(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Override
    @Transactional(readOnly = true)
    public Role findByTitle(String title) {
        return roleDAO.findByTitle(title);
    }

    @Override
    @Transactional
    public Role findById(Integer id) {
        return roleDAO.findById(id);
    }

    @Override
    @Transactional
    public List<Role> findAll() {
        return roleDAO.findAll();
    }
}
