package com.desj.controller;

import com.desj.model.UserRepository;
import com.desj.service.LearningGroupService;
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
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private LearningGroupService learningGroupService;

    @RequestMapping("/")
    public String welcomeUser(Model model) {
        try {
            model.addAttribute("username", userService.getCurrentDesjUser().getUsername());
        }
        catch (Exception e){

        }

        model.addAttribute("allUser", userRepository.findAll());
        model.addAttribute("learningGroupsOfUser",
                learningGroupService.getAllLearningGroupsOfUser(userService.getCurrentDesjUser()));
        model.addAttribute("numberOfGroups", learningGroupService.getAllLearningGroupsOfUser(userService.getCurrentDesjUser()).size());
        return "welcome";
    }


}
