package com.desj.controller;

import com.desj.model.*;
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
import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private GroupPostRepository groupPostRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    MCQuestionRepository mcQuestionRepository;

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

        for (MCQuestion mcQuestion : mcQuestionRepository.findAll()) {
            if (mcQuestion.getCorrespondingLearningGroup().getId() == learningGroupId) {
                mcQuestionRepository.delete(mcQuestion);
            }
        }

        for (Comment comment : commentRepository.findAll()) {
            if (comment.getAssociatedGroupPost().getAssociatedLearningGroup().getId() == learningGroupId) {
                commentRepository.delete(comment);
            }
        }

        List<GroupPost> groupPosts = new ArrayList<>();
        groupPosts.addAll(groupPostRepository.findAll());
        for (GroupPost groupPost : groupPosts) {
            if (groupPost.getAssociatedLearningGroup().getId() == learningGroupId) {
                groupPostRepository.delete(groupPost);
            }
        }
        learningGroupRepository.delete(learningGroupRepository.findOne(learningGroupId));
        return "redirect:/showUserSite";
    }

    @RequestMapping(value = "/deleteUserProfile")
    @Transactional
    public String deleteUserProfile() {

        userDetailsManager.deleteUser(userService.getCurrentDesjUser().getEmail());

        return "redirect:/login?logout";
    }

}
