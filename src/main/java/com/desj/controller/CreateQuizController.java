package com.desj.controller;

import com.desj.model.LearningGroupRepository;
import com.desj.model.Quiz;
import com.desj.service.QuizService;
import com.desj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Desi on 6/15/2016.
 */
@Controller
public class CreateQuizController {
    @Autowired
    private UserService userService;

    @Autowired
    private QuizService quizService;
    @Autowired
    private LearningGroupRepository learningGroupRepository;


    @RequestMapping(value = "/createMCTest{id}", method = RequestMethod.GET)
    public String showCreateMCTest(@RequestParam(value = "id", required = false) Integer id, Model model) {
        model.addAttribute("ussername", userService.getCurrentDesjUser().getId());
        model.addAttribute("mcTest", new Quiz());
        return "CreateMCTest";
    }

    @RequestMapping(value = "/createNewMCTest{learningGroupId}", method = RequestMethod.POST)
    public String createNewMCTest(@RequestParam (value = "id") Integer learningGroupId,@ModelAttribute("createdMCTest") Quiz quiz) {
        quizService.save(quiz, learningGroupRepository.findOne(learningGroupId));
        return "redirect://createMCTest?id=" + quiz.getId().toString();
    }
}
