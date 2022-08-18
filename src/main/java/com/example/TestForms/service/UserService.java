package com.example.TestForms.service;

import com.example.TestForms.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<User> findAll();
    User findById(Integer id);
    User findByEmail(String email);
    User getCurrentUser();
    void save(User user);
    void update(User user);
    void delete(User user);

    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
