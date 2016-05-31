package com.desj.controller;

import com.desj.model.LearningGroupRepository;
import com.desj.service.LearningGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Julien on 24.05.16.
 */
@Controller
public class ShowLearningGroupController {

    @Autowired
    private LearningGroupRepository learningGroupRepository;

    @Autowired
    private LearningGroupService learningGroupService;

    @RequestMapping("/showLearningGroup{id}")
    public String showLearningGroup(@RequestParam("id") Integer learningGroupId, Model model) {
        model.addAttribute("learningGroup", learningGroupRepository.findOne(learningGroupId));
        model.addAttribute("learningGroupMembers", learningGroupService.getAllMemberOfLearningGroup(learningGroupId));
        return "ShowLearningGroup";
    }
}
