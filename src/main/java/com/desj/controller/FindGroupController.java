package com.desj.controller;

import com.desj.service.LearningGroupService;
import com.desj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Julien on 05.06.16.
 */
@Controller
public class FindGroupController {

    @Autowired
    private LearningGroupService learningGroupService;

    @Autowired
    private UserService userService;

    @RequestMapping("/findGroup")
    public String findGroup(Model model) {
        model.addAttribute("newLearningGroups", learningGroupService.getNewLearningGroups(userService.getCurrentDesjUser()));
        return "/findGroup";
    }
}
