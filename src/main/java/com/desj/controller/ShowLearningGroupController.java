package com.desj.controller;

import com.desj.model.*;
import com.desj.service.CommentService;
import com.desj.service.GroupPostService;
import com.desj.service.LearningGroupService;
import com.desj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Julien on 24.05.16.
 */
@Controller
public class ShowLearningGroupController {

    @Autowired
    UserService userService;

    @Autowired
    private LearningGroupRepository learningGroupRepository;

    @Autowired
    private LearningGroupService learningGroupService;

    @Autowired
    private GroupPostRepository groupPostRepository;

    @Autowired
    private GroupPostService groupPostService;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private CommentService commentService;



    @RequestMapping("/showLearningGroup{id}")
    public String showLearningGroup(@RequestParam(value = "id") Integer learningGroupId, Model model) {

        model.addAttribute("username", userService.getCurrentDesjUser().getUsername());
        model.addAttribute("learningGroup", learningGroupRepository.findOne(learningGroupId));
        model.addAttribute("learningGroupMembers", learningGroupService.getAllMemberOfLearningGroup(learningGroupId));
        model.addAttribute("newGroupPost", new GroupPost());
        model.addAttribute("comment", new Comment());
        model.addAttribute("groupPosts", groupPostService.getAllGroupPostsOfLearningGroup(learningGroupRepository
                .findOne(learningGroupId)));
        model.addAttribute("comments", commentRepository.findAll());
        return "ShowLearningGroup";
    }

    @RequestMapping(value = "/newGroupPost{learningGroupId}", method = RequestMethod.POST)
    public String writeNewGroupPost(@RequestParam(value = "learningGroupId") Integer learningGroupId,
                                    @ModelAttribute("groupPost") GroupPost groupPost, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "ShowLearningGroup";
        } else {
            groupPostService.save(groupPost, userService.getCurrentDesjUser(), learningGroupRepository
                    .findOne(learningGroupId));

            return "redirect:/showLearningGroup?id=" + learningGroupId.toString();
        }
    }

    @RequestMapping(value = "/newComment{groupPostId}", method = RequestMethod.POST)
    public String writeNewComment(@RequestParam(value = "groupPostId") Integer groupPostId,
                                  @ModelAttribute("comment") Comment comment, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "ShowLearningGroup";
        } else {

            commentService.save(comment, userService.getCurrentDesjUser(), groupPostRepository.findOne(groupPostId));
            groupPostService.addGroupPostComment(groupPostRepository.findOne(groupPostId), comment);
            String redirectString = groupPostRepository.findOne(groupPostId).getAssociatedLearningGroup().getId().toString();
            return "redirect:/showLearningGroup?id=" + redirectString;
        }
    }



}
