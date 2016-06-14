package com.desj.controller;

import com.desj.model.LearningGroupRepository;
import com.desj.model.User;
import com.desj.service.LearningGroupService;
import com.desj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Julien on 05.06.16.
 */
@Controller
public class FindGroupController {

    @Autowired
    LearningGroupRepository learningGroupRepository;

    @Autowired
    private LearningGroupService learningGroupService;

    @Autowired
    private UserService userService;

    @RequestMapping("/findGroup")
    public String findGroup(Model model) {
        model.addAttribute("username", userService.getCurrentDesjUser().getUsername());
        model.addAttribute("user", userService.getCurrentDesjUser());
        model.addAttribute("newLearningGroups", learningGroupService.getNewLearningGroups(userService.getCurrentDesjUser()));
        return "/findGroup";
    }

    @RequestMapping(value = "/becomeMember{learningGroupId}")
    public String becomeMember(@RequestParam("learningGroupId")Integer learningGroupId) {
        User user = userService.getCurrentDesjUser();
        learningGroupService.addMemberToLearningGroup(learningGroupId, user);
        return "redirect:/findGroup";
    }
}
