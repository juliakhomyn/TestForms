package com.example.TestForms.controller;

import com.example.TestForms.model.Session;
import com.example.TestForms.model.Test;
import com.example.TestForms.service.OptionService;
import com.example.TestForms.service.SessionService;
import com.example.TestForms.service.TestService;
import com.example.TestForms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/tests")
public class TestController {

    private final TestService testService;
    private final UserService userService;
    private final SessionService sessionService;
    private final OptionService optionService;

    @Autowired
    public TestController(TestService testService, UserService userService, SessionService sessionService, OptionService optionService) {
        this.testService = testService;
        this.userService = userService;
        this.sessionService = sessionService;
        this.optionService = optionService;
    }

    @GetMapping()
    public String allTests(Model model) {
        model.addAttribute("tests", testService.findActive());
        return "all_tests";
    }

    @GetMapping("/take/{id}")
    public String takeTest(Model model, @PathVariable Integer id) {
        Session session = new Session();
        Test test = testService.findById(id);
        session.setUser(userService.getCurrentUser());
        session.setTest(test);
        session.setStartTime(LocalDateTime.now());
        sessionService.add(session);
        model.addAttribute("session", session);
        model.addAttribute("test", test);
        model.addAttribute("questions", test.getQuestions());
        return "test_take";
    }

    @PostMapping("/result/{id}")
    public String submitTest(HttpServletRequest request, @PathVariable Integer id) {
        Session session = sessionService.findById(id);
        session.setEndTime(LocalDateTime.now());
        session.setChoices(optionService.checkAnswers(session, request));
        session.setScore(sessionService.calcScore(session));
        sessionService.update(session);
        return "redirect:/tests/result/{id}";
    }

    @GetMapping("/result/{id}")
    public String getResult(Model model, @PathVariable Integer id) {
        Session session = sessionService.findById(id);
        Test test = session.getTest();
        String message = "";
        if (test.getPassingScore() <= session.getScore()) {
            message = "Congratulations! You've passed the test!";
        } else {
            message = "The score is not high enough! Try one more time";
        }
        model.addAttribute("message", message);
        model.addAttribute("session", session);
        model.addAttribute("test", test);
        model.addAttribute("questions", test.getQuestions());
        model.addAttribute("choices", session.getChoices());
        return "test_result";
    }

}
