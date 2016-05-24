package com.desj.controller;

import com.desj.model.LearningGroupRepository;
import com.desj.model.UserRepository;
import com.desj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Julien on 25.04.16.
 */
@Controller
public class WelcomeController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    LearningGroupRepository learningGroupRepository;

    @RequestMapping("/")
    public String welcomeUser(Model model) {
        model.addAttribute("username", userService.getCurrentDesjUser().getUsername());
        model.addAttribute("allUser", userRepository.findAll());
        model.addAttribute("learningGroups", learningGroupRepository.findAll());
        return "welcome";
    }


}
