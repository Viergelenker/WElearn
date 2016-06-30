package com.desj.controller;

import com.desj.model.*;
import com.desj.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Julien on 30.06.16.
 */
@Controller
public class StatisticsController {

    @Autowired
    private UserService userService;
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
    @Autowired
    private MCQuestionService mcQuestionService;
    @Autowired
    private QuestionReposiory questionReposiory;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private QuestionCommentService questionCommentService;
    @Autowired
    private QuizService quizService;

    @RequestMapping(value = "/showStatistics")
    public String showStatistics(@RequestParam("learningGroupId") Integer learningGroupId, Model model) {

        User currentUser = userService.getCurrentDesjUser();
        LearningGroup currentLearningGroup = learningGroupRepository.findOne(learningGroupId);

        model.addAttribute("learningGroupMembers", learningGroupService.getAllMemberOfLearningGroup(learningGroupId));
        model.addAttribute("pointsOfCurrentUser", userService.getTotalOfQuizPointsForUserForLearningGroup(currentUser, currentLearningGroup));
        model.addAttribute("quizzesOfCurrentUser", userService.getAllQuizzesOfUserInLearningGroup(currentUser,
                currentLearningGroup));
        // model.addAttribute("averagePoints", userService.calculateAveragePoints(currentUser, currentLearningGroup));
        model.addAttribute("quizzesOfAllMember", quizService.getAllQuizesOfLearningGroup(currentLearningGroup));

        return "Statistics";
    }
}