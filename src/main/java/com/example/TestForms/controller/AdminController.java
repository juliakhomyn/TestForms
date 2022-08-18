package com.example.TestForms.controller;

import com.example.TestForms.model.Category;
import com.example.TestForms.model.Session;
import com.example.TestForms.model.Test;
import com.example.TestForms.model.User;
import com.example.TestForms.service.RoleService;
import com.example.TestForms.service.SessionService;
import com.example.TestForms.service.TestService;
import com.example.TestForms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/management")
public class AdminController {

    private final TestService testService;
    private final UserService userService;
    private final SessionService sessionService;
    private final RoleService roleService;

    @Autowired
    public AdminController(TestService testService, UserService userService, SessionService sessionService, RoleService roleService) {
        this.testService = testService;
        this.userService = userService;
        this.sessionService = sessionService;
        this.roleService = roleService;
    }

    @GetMapping("/tests")
    public String allTests(Model model) {
        model.addAttribute("tests", testService.findAll());
        model.addAttribute("formatter", DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        return "test_list";
    }

    @GetMapping("/tests/add")
    public String showFormAddTest(Model model) {
        model.addAttribute("test", new Test());
        model.addAttribute("categories", Category.values());
        return "test_add";
    }

    @PostMapping("/tests/add")
    public String addTest(@Valid @ModelAttribute Test test, BindingResult bindingResult, HttpServletRequest request, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", Category.values());
            return "test_add";
        }
        List<String> questions = Arrays.asList(request.getParameterValues("question"));
        List<String> questionIds = Arrays.asList(request.getParameterValues("questionId"));
        List<String> options = Arrays.asList(request.getParameterValues("option"));
        List<String> answers = Arrays.asList(request.getParameterValues("checkbox"));
        testService.createTest(test, questions, questionIds, options, answers, userService.getCurrentUser());
        return "redirect:/management/tests";
    }

    @GetMapping("/tests/{id}")
    public String testDetails(Model model, @PathVariable Integer id) {
        model.addAttribute("test", testService.findById(id));
        model.addAttribute("formatter", DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        return "test_details";
    }

    @GetMapping("/tests/edit/{id}")
    public String editTestShow(Model model, @PathVariable Integer id) {
        model.addAttribute("test", testService.findById(id));
        model.addAttribute("formatter", DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        model.addAttribute("categories", Arrays.asList(Category.values()));
        return "test_edit";
    }

    @PostMapping("/tests/edit/{id}")
    public String editTest(@PathVariable Integer id, @RequestParam String title, @RequestParam Double passingScore, @RequestParam Category category, @RequestParam(required = false) boolean isActive) {
        Test test = testService.findById(id);
        test.setTitle(title);
        test.setPassingScore(passingScore);
        test.setCategory(category);
        test.setActive(isActive);
        testService.update(test);
        return "redirect:/management/tests";
    }

    @RequestMapping("/results")
    public String allResults(Model model, @RequestParam(required = false) String start, @RequestParam(required = false) String end) {
        LocalDate startDate;
        LocalDate endDate;
        List<Session> sessions;
        if (start == null || end == null || start.equals("") || end.equals("")) {
            sessions = sessionService.findAll();
        } else {
            startDate = LocalDate.parse(start);
            endDate = LocalDate.parse(end);
            sessions = sessionService.findByDateRange(startDate, endDate);
        }
        model.addAttribute("timeSpent", sessionService.getSpentTime(sessions));
        model.addAttribute("formatter", DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        model.addAttribute("sessions", sessions);
        return "result_list";
    }

    @GetMapping("/users")
    public String allUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "user_list";
    }

    @GetMapping("/users/{id}")
    public String getUserDetails(Model model, @PathVariable Integer id) {
        User user = userService.findById(id);
        List<Session> sessions = sessionService.findByUserId(id);
        model.addAttribute("user", user);
        model.addAttribute("sessions", sessions);
        model.addAttribute("timeSpent", sessionService.getSpentTime(sessions));
        model.addAttribute("formatter", DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        model.addAttribute("role", user.getRole());
        model.addAttribute("roles", roleService.findAll());
        return "user_details";
    }

    @GetMapping("/users/edit/{id}")
    public String editUserShow(Model model, @PathVariable Integer id) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        model.addAttribute("role", user.getRole());
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("currentUser", userService.getCurrentUser());
        return "user_edit";
    }

    @PostMapping("/users/edit/{id}")
    public String editUser(@PathVariable Integer id, @RequestParam String name, @RequestParam String surname, @RequestParam String email, @RequestParam(required = false) String role) {
        User user = userService.findById(id);
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
        if (role != null) {
            user.setRole(roleService.findById(Integer.parseInt(role)));
        }
        userService.update(user);
        return "redirect:/management/users";
    }

}
