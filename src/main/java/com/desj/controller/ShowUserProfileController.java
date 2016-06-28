package com.desj.controller;

import com.desj.model.User;
import com.desj.model.UserRepository;
import com.desj.service.LearningGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Julien on 27.06.16.
 */
@Controller
public class ShowUserProfileController {

    @Autowired
    private LearningGroupService learningGroupService;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/showUserProfile")
    public String showUserSite(@RequestParam("userId") Integer userId, Model model) {

        User selectedUser = userRepository.findOne(userId);

        model.addAttribute("user", selectedUser);

        model.addAttribute("learningGroupsOfUser", learningGroupService.getAllLearningGroupsOfUser(selectedUser));

        return "ShowUserProfile";
    }
}

