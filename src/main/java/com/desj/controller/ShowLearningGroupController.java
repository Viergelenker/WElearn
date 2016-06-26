package com.desj.controller;

import com.desj.model.*;
import com.desj.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

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
    @Autowired
    private MCQuestionRepository mcQuestionRepository;
    @Autowired
    private MCQuestionService mcQuestionService;
    @Autowired
    private QuestionReposiory questionReposiory;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private QuizService quizService;
    @Autowired
    private QuizRepository quizRepository;



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
                                    @Valid @ModelAttribute("groupPost") GroupPost groupPost, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "redirect:/showLearningGroup?id=" + learningGroupId.toString();
        } else {
            groupPostService.save(groupPost, userService.getCurrentDesjUser(), learningGroupRepository
                    .findOne(learningGroupId));

            return "redirect:/showLearningGroup?id=" + learningGroupId.toString();
        }
    }

    @RequestMapping(value = "/newComment{groupPostId}", method = RequestMethod.POST)
    public String writeNewComment(@RequestParam(value = "groupPostId") Integer groupPostId,
                                  @Valid @ModelAttribute("comment") Comment comment, BindingResult bindingResult) {

        String redirectString = groupPostRepository.findOne(groupPostId).getAssociatedLearningGroup().getId().toString();

        if (bindingResult.hasErrors()) {
            return "redirect:/showLearningGroup?id=" +redirectString;
        } else {

            commentService.save(comment, userService.getCurrentDesjUser(), groupPostRepository.findOne(groupPostId));
            groupPostService.addGroupPostComment(groupPostRepository.findOne(groupPostId), comment);

            return "redirect:/showLearningGroup?id=" + redirectString;
        }
    }

    @RequestMapping(value = "/newMCQuestion{learningGroupId}", method = RequestMethod.POST)
    public String whriteNewMCQuestion(@RequestParam(value = "learningGroupId") Integer learningGroupId, @ModelAttribute("mcQuestion") MCQuestion mcQuestion){
        User user = userService.getCurrentDesjUser();
        mcQuestionService.save(mcQuestion,learningGroupRepository.findOne(learningGroupId),user);
        user.createMCQuestion(mcQuestion);

        return "redirect:/showLearningGroup?id=" +learningGroupId.toString();
    }

    @RequestMapping(value = "/newQuestion{learningGroupId}", method = RequestMethod.POST)
    public String whriteNewQuestion(@RequestParam(value = "learninGroupId") Integer learningGroupId, @ModelAttribute("Question") Question question){
        User user= userService.getCurrentDesjUser();
        questionService.save(question,learningGroupRepository.findOne(learningGroupId),user);
        user.createQuestion(question);

        return "redirect:/showLearningGroup?id=" +learningGroupId.toString();
    }

    @RequestMapping(value = "/startQuiz{learningGroupId}", method = RequestMethod.POST)
    public String startQuiz(@RequestParam(value = "learningGroupId") Integer learningGroupId, @ModelAttribute("Quiz") Quiz quiz){
        quizService.save(quiz,learningGroupRepository.findOne(learningGroupId));


        return "redirect:/showLearningGroup?id=" +learningGroupId.toString();
    }



}
