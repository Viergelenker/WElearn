package com.desj.controller;

import com.desj.model.LearningGroupRepository;
import com.desj.model.Question;
import com.desj.model.User;
import com.desj.service.QuestionService;
import com.desj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Julien on 07.07.16.
 */
@Controller
public class ShowQuestionController {

    @Autowired
    private UserService userService;

    @Autowired
    private LearningGroupRepository learningGroupRepository;

    @Autowired
    private QuestionService questionService;

    @RequestMapping(value = "/showQuestion")
    public String showQuestion(@RequestParam("learningGroupId") Integer learningGroupId, Model model) {

        User currentUser = userService.getCurrentDesjUser();
        model.addAttribute("learningGroup", learningGroupRepository.findOne(learningGroupId));
        Question question = questionService.getQuestion(learningGroupRepository.findOne(learningGroupId), currentUser);
        if (question != null) {
            List<Question> answeredQuestions = new ArrayList<>();
            answeredQuestions.addAll(userService.getCurrentDesjUser().getAnsweredQuestions());
            answeredQuestions.add(question);
            userService.getCurrentDesjUser().setAnsweredQuestions(answeredQuestions);
            model.addAttribute("question", question);
            model.addAttribute("error", false);

        }
        else {
            model.addAttribute("error", true);
        }

        return "ShowQuestion";
    }


}
