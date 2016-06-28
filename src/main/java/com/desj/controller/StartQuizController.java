package com.desj.controller;

import com.desj.model.LearningGroupRepository;
import com.desj.model.Quiz;
import com.desj.service.QuestionService;
import com.desj.service.QuizService;
import com.desj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Desi on 6/15/2016.
 */
@Controller
public class StartQuizController {
    @Autowired
    private UserService userService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuizService quizService;
    @Autowired
    private LearningGroupRepository learningGroupRepository;


    @RequestMapping(value = "/startQuiz", method = RequestMethod.GET)
    public String startQuiz(@RequestParam(value = "learningGroupId") Integer learningGroupId,
                            @RequestParam(value = "questionQuantity") Integer questionQuantity,
                            Model model) {

        Map<Integer, String> map = new HashMap<>();

        Quiz quiz = new Quiz();
        quiz.setMCQuestions(quizService.getQuestions(userService.getCurrentDesjUser(),learningGroupId,questionQuantity));

        model.addAttribute("quiz", quiz);
        model.addAttribute("givenAnswers", map);
        model.addAttribute("questions", quiz.getMCQuestions());

        return "StartQuiz";
    }

    /*@RequestMapping(value = "/createNewMCTest{learningGroupId}", method = RequestMethod.POST)
    public String createNewMCTest(@RequestParam (value = "id") Integer learningGroupId,@ModelAttribute("createdMCTest") Quiz quiz) {
        quizService.save(quiz, learningGroupRepository.findOne(learningGroupId));
        return "redirect://createMCTest?id=" + quiz.getId().toString();
    }*/
}
