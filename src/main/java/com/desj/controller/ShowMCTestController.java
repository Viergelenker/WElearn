package com.desj.controller;

import com.desj.model.LearningGroupRepository;
import com.desj.model.MCQuestionRepository;
import com.desj.model.MCTestRepository;
import com.desj.service.MCTestService;
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
    private MCTestRepository mcTestRepository;
    @Autowired
    private MCTestService mcTestService;
    @Autowired
    private UserService userService;
    @Autowired
    private LearningGroupRepository learningGroupRepository;
    @Autowired
    private MCQuestionRepository mcQuestionRepository;

    @RequestMapping("/showMCTest{id}")
    public String showLMCTest(@RequestParam(value = "id" )Integer mcTestID, Model model){
        model.addAttribute("username",userService.getCurrentDesjUser().getUsername());
        model.addAttribute("learningGroup", learningGroupRepository.findOne(mcTestRepository.findOne(mcTestID).getLearningGroup().getId()));
        model.addAttribute("creator",mcTestRepository.findOne(mcTestID).getCreator().getId());
        model.addAttribute("mcTest", mcTestRepository.findOne(mcTestID));


        return "showMCTest";
    }

}
