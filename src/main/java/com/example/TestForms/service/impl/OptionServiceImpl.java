package com.example.TestForms.service.impl;

import com.example.TestForms.dao.OptionDAO;
import com.example.TestForms.model.Option;
import com.example.TestForms.model.Session;
import com.example.TestForms.service.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class OptionServiceImpl implements OptionService {

    private final OptionDAO optionDAO;

    @Autowired
    public OptionServiceImpl(OptionDAO optionDAO) {
        this.optionDAO = optionDAO;
    }

    @Override
    @Transactional
    public List<Option> findAll() {
        return optionDAO.findAll();
    }

    @Override
    @Transactional
    public Option findById(Integer id) {
        return optionDAO.findById(id);
    }

    @Override
    @Transactional
    public void save(Option option) {
        optionDAO.save(option);
    }

    @Override
    @Transactional
    public void save(Set<Option> options) {
        for (Option option : options) {
            save(option);
        }
    }

    @Override
    @Transactional
    public void update(Option option) {
        optionDAO.update(option);
    }

    @Override
    @Transactional
    public LinkedHashSet<Option> checkAnswers(Session session, HttpServletRequest request) {
        List<Option> options = new ArrayList<>();
        for (int i = 0; i < session.getTest().getQuestions().size(); i++) {
            if (request.getParameterValues("question_" + (i + 1)) == null) {
                options.add(null);
            } else {
                for (int j = 0; j < request.getParameterValues("question_" + (i + 1)).length; j++) {
                    options.add(findById(Integer.parseInt(request.getParameterValues("question_" + (i + 1))[j])));
                }
            }
        }
        return new LinkedHashSet<>(options);
    }
}
