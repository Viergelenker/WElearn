package com.desj.controller;

import com.desj.model.LearningGroupRepository;
import com.desj.model.MCQuestionRepository;
import com.desj.model.QuizRepository;
import com.desj.service.QuizService;
import com.desj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Desi on 6/15/2016.
 */
@Controller
public class ShowMCTestController {
    @Autowired
    private QuizRepository quizRepository;
    @Autowired
    private QuizService quizService;
    @Autowired
    private UserService userService;
    @Autowired
    private LearningGroupRepository learningGroupRepository;
    @Autowired
    private MCQuestionRepository mcQuestionRepository;

    @RequestMapping("/showMCTest{id}")
    public String showLMCTest(@RequestParam(value = "id" )Integer mcTestID, Model model){
        model.addAttribute("username",userService.getCurrentDesjUser().getUsername());
        model.addAttribute("learningGroup", learningGroupRepository.findOne(quizRepository.findOne(mcTestID).getLearningGroup().getId()));
        model.addAttribute("creator", quizRepository.findOne(mcTestID).getCreator().getId());
        model.addAttribute("mcTest", quizRepository.findOne(mcTestID));


        return "showMCTest";
    }

}
