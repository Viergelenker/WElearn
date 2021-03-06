package com.desj.controller;

import com.desj.model.LearningGroup;
import com.desj.model.LearningGroupRepository;
import com.desj.model.User;
import com.desj.service.LearningGroupService;
import com.desj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @Autowired
    private BCryptPasswordEncoder encoder;

    @RequestMapping("/findGroup")
    public String findGroup(@RequestParam(value = "wrongPassword", required = false) Boolean wrongPassword, Model model) {
        model.addAttribute("username", userService.getCurrentDesjUser().getUsername());
        model.addAttribute("user", userService.getCurrentDesjUser());
        model.addAttribute("newLearningGroups", learningGroupService.getNewLearningGroups(userService.getCurrentDesjUser()));
        model.addAttribute("wrongPassword", wrongPassword);
        return "FindGroup";
    }

    @RequestMapping(value = "/becomeMember")
    public String becomeMember(@RequestParam("learningGroupId")Integer learningGroupId) {
        User user = userService.getCurrentDesjUser();
        learningGroupService.addMemberToLearningGroup(learningGroupId, user);
        return "redirect:/findGroup";
    }

    @RequestMapping(value = "/becomeMemberOfPrivateGroup", method = RequestMethod.POST)
    public String becomeMemberOfPrivateGroup(@RequestParam("learningGroupId")Integer learningGroupId,
                                             LearningGroup learningGroup) {

        if (encoder.matches(learningGroup.getPassword(), learningGroupRepository.findOne(learningGroupId).getPassword())) {
            User user = userService.getCurrentDesjUser();
            learningGroupService.addMemberToLearningGroup(learningGroupId, user);
            return "redirect:/findGroup";
        }
        else {
            return "redirect:/findGroup?wrongPassword=true";
        }

    }
}
