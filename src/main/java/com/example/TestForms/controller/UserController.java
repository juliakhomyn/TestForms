package com.example.TestForms.controller;

import com.example.TestForms.model.User;
import com.example.TestForms.service.RoleService;
import com.example.TestForms.service.SessionService;
import com.example.TestForms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/home")
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/edit/{id}")
    public String editBook(Model model, @PathVariable Integer id) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        model.addAttribute("role", user.getRole());
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("currentUser", userService.getCurrentUser());
        return "user_edit";
    }

    @PostMapping("/edit/{id}")
    public String editBook(@PathVariable Integer id, @RequestParam String name, @RequestParam String surname, @RequestParam String email, @RequestParam(required = false) String role) {
        User user = userService.findById(id);
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
        if (role != null) {
            user.setRole(roleService.findById(Integer.parseInt(role)));
        }
        userService.update(user);
        return "redirect:/home";
    }
}
