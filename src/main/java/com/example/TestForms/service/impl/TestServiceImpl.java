package com.example.TestForms.service.impl;

import com.example.TestForms.dao.TestDAO;
import com.example.TestForms.model.Option;
import com.example.TestForms.model.Question;
import com.example.TestForms.model.Test;
import com.example.TestForms.model.Type;
import com.example.TestForms.model.User;
import com.example.TestForms.service.OptionService;
import com.example.TestForms.service.QuestionService;
import com.example.TestForms.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TestServiceImpl implements TestService {

    private final TestDAO testDAO;
    private final QuestionService questionService;
    private final OptionService optionService;

    @Autowired
    public TestServiceImpl(TestDAO testDAO, QuestionService questionService, OptionService optionService) {
        this.testDAO = testDAO;
        this.questionService = questionService;
        this.optionService = optionService;
    }

    @Override
    @Transactional
    public List<Test> findAll() {
        return testDAO.findAll();
    }

    @Override
    @Transactional
    public List<Test> findActive() {
        return testDAO.findActive();
    }

    @Override
    @Transactional
    public Test findById(Integer id) {
        return testDAO.findById(id);
    }

    @Override
    @Transactional
    public void save(Test test) {
        testDAO.save(test);
    }

    @Override
    @Transactional
    public void update(Test test) {
        testDAO.update(test);
    }

    @Override
    @Transactional
    public void delete(Test test) {
        testDAO.delete(test);
    }

    @Override
    @Transactional
    public void createTest(Test test, List<String> questions, List<String> questionIds, List<String> options, List<String> answers, User user) {
        double passingScore = questions.size() / 100.00 * test.getPassingScore();
        test.setCreatedBy(user);
        test.setCreatedDate(LocalDateTime.now());
        test.setPassingScore(Math.round(passingScore * 100) / 100D);
        save(test);

        System.out.println("-------------------CREATE TEST-----------------------");
        Set<Integer> answersSet = answers.stream()
                            .map(Integer::parseInt)
                            .collect(Collectors.toSet());
        List<Question> questionList = new ArrayList<>();
        List<Option> optionList = new ArrayList<>();
        int questionIndex = 1;
        for (String title : questions) {
            int correct = 0;
            int wrong = 0;
            Question question = new Question();
            question.setTitle(title);
            questionService.save(question);
            for (int i = 0; i < options.size(); i++) {
                if (Integer.parseInt(questionIds.get(i)) == questionIndex) {
                    Option option = new Option();
                    option.setTitle(options.get(i));
                    option.setQuestion(question);
                    if (answersSet.contains(i + 1)) {
                        option.setAnswer(true);
                        correct++;
                    } else {
                        option.setAnswer(false);
                        wrong++;
                    }
                    optionService.save(option);
                    question.getOptions().add(option);
                    optionList.add(option);
                }
            }
            question.setType(correct == 1 ? Type.SINGLE_ANSWER : Type.MULTIPLE_ANSWERS);
            questionService.update(question);
            for (Option o : question.getOptions()) {
                if (question.getType().getType().equals("SINGLE_ANSWER")) {
                    o.setValue(o.isAnswer() ? 1.0 : 0);
                } else {
                    o.setValue(o.isAnswer() ? Math.round(1.0 / correct * 100) / 100D : -Math.round(1.0 / wrong * 100) / 100D);
                }
                optionService.update(o);
            }
            questionIndex++;
            questionList.add(question);
        }

        test.setQuestions(new LinkedHashSet<>(questionList));
        update(test);
    }
}
