package com.desj.controller;

import com.desj.model.LearningGroupRepository;
import com.desj.model.UserRepository;
import com.desj.service.LearningGroupService;
import com.desj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.UserDetailsManager;
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
    private LearningGroupService learningGroupService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsManager userDetailsManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @RequestMapping("/showUserSite")
    public String showUserSite(Model model) {

        model.addAttribute("user", userService.getCurrentDesjUser());

        model.addAttribute("learningGroupsUserHasCreated", learningGroupService
                .getAllLearningGroupsUserHasCreated(userService.getCurrentDesjUser()));

        return "ShowUserSite";
    }

    @RequestMapping(value = "/deleteLearningGroup{learningGroupId}")
    @Transactional
    public String deleteLearningGroup(@RequestParam(value = "learningGroupId") Integer learningGroupId) {

        learningGroupRepository.removeById(learningGroupId);
        return "redirect:/showUserSite";
    }

    @RequestMapping(value = "/deleteUserProfile")
    @Transactional
    public String deleteUserProfile() {

        userDetailsManager.deleteUser(userService.getCurrentDesjUser().getEmail());

        return "redirect:/login?logout";
    }

}
