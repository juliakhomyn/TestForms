package com.example.TestForms.controller;

import com.example.TestForms.model.Session;
import com.example.TestForms.model.User;
import com.example.TestForms.service.RoleService;
import com.example.TestForms.service.SessionService;
import com.example.TestForms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    private final UserService userService;
    private final RoleService roleService;
    private final SessionService sessionService;

    @Autowired
    public HomeController(UserService userService, RoleService roleService, SessionService sessionService) {
        this.userService = userService;
        this.roleService = roleService;
        this.sessionService = sessionService;
    }

    @GetMapping("/registration")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public  String addUser(@Valid @ModelAttribute User user, BindingResult bindingResult, Map<String, Object> model) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            return "registration";
        }
        System.out.println(bindingResult.getAllErrors());
        if (userService.findByEmail(user.getEmail()) != null){
            model.put("message", "User with this email already exists");
            return "registration";
        }
        user.setCreatedDate(LocalDateTime.now());
        user.setRole(roleService.findByTitle("USER"));
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12)));
        userService.save(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "logout", required = false) String logout,
                            Model model) {
        String errorMessage = null;
        String logoutMessage = null;
        if(error != null) {
            errorMessage = "Invalid e-mail or password";
        }
        if(logout != null) {
            logoutMessage = "You have been logged out";
        }
        model.addAttribute("errorMessage", errorMessage);
        model.addAttribute("logoutMessage", logoutMessage);
        return "login";
    }

    @PostMapping("/login")
    public String redirection() {
        return "redirect:/default";
    }

    @RequestMapping("/default")
    public String defaultAfterLogin(){
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String home(Model model) {
        User user = userService.getCurrentUser();
        if (user == null) {
            return "redirect:/login";
        }
        List<Session> sessions = sessionService.findByUserId(user.getId());
        model.addAttribute("timeSpent", sessionService.getSpentTime(sessions));
        model.addAttribute("user", user);
        model.addAttribute("sessions", sessions);
        model.addAttribute("formatter", DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        return "home";
    }

    @GetMapping("/logout")
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout=true";
    }

}
