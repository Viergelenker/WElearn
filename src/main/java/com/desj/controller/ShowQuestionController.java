package com.desj.controller;

import com.desj.model.*;
import com.desj.service.QuestionCommentService;
import com.desj.service.QuestionService;
import com.desj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Julien on 07.07.16.
 */
@Controller
public class ShowQuestionController {

    @Autowired
    private UserService userService;

    @Autowired
    private LearningGroupRepository learningGroupRepository;

    @Autowired
    private QuestionReposiory questionReposiory;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuestionCommentService questionCommentService;

    @RequestMapping(value = "/showQuestion")
    public String showQuestion(@RequestParam("learningGroupId") Integer learningGroupId,
                               @RequestParam(value = "questionId", required = false) Integer questionId, Model model) {

        User currentUser = userService.getCurrentDesjUser();
        model.addAttribute("learningGroup", learningGroupRepository.findOne(learningGroupId));

        // Used to display the same question again, after writing a comment
        if (questionId != null) {
            model.addAttribute("user", currentUser.getId());
            model.addAttribute("question", questionReposiory.findOne(questionId));
            model.addAttribute("error", false);
            model.addAttribute("creator", userRepository.getOne(questionReposiory.findOne(questionId).getCreator()
                    .getId()));
            model.addAttribute("questionComment", new QuestionComment());
            model.addAttribute("allQuestionComments", questionCommentService.getAllCommentsOfQuestion(questionReposiory
                    .findOne(questionId)));

        } else {
            Question question = questionService.getQuestion(learningGroupRepository.findOne(learningGroupId));
            if (question != null) {
                model.addAttribute("user", currentUser.getId());
                model.addAttribute("question", question);
                model.addAttribute("error", false);
                model.addAttribute("creator", userRepository.getOne(question.getCreator().getId()));
                model.addAttribute("questionComment", new QuestionComment());
                model.addAttribute("allQuestionComments", questionCommentService.getAllCommentsOfQuestion(question));

            } else {
                model.addAttribute("error", true);
            }
        }
        return "ShowQuestion";
    }
}
