package com.desj.controller;

import com.desj.model.LearningGroupRepository;
import com.desj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;

/**
 * Created by Julien on 14.06.16.
 */
@Controller
public class ShowUserSiteController {

    @Autowired
    private LearningGroupRepository learningGroupRepository;
    @Autowired
    private UserService userService;

    @RequestMapping("/showUserSite")
    public String showUserSite(Model model) {

        model.addAttribute("user", userService.getCurrentDesjUser());

        model.addAttribute("learningGroupsUserHasCreated", userService.getAllGroupsUserHasCreated(userService.getCurrentDesjUser()));


        return "ShowUserSite";
    }

    @RequestMapping(value = "/deleteLearningGroup{learningGroupId}")
    @Transactional
    public String deleteLearningGroup(@RequestParam(value = "learningGroupId") Integer learningGroupId) {

        learningGroupRepository.delete(learningGroupId);
        return "";
    }

}
