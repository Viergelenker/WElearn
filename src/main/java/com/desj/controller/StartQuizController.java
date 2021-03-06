package com.desj.controller;

import com.desj.model.LearningGroupRepository;
import com.desj.model.MCQuestion;
import com.desj.model.Quiz;
import com.desj.service.QuizService;
import com.desj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Desi on 6/15/2016.
 */
@Controller
public class StartQuizController {

    @Autowired
    private UserService userService;

    @Autowired
    private LearningGroupRepository learningGroupRepository;

    @Autowired
    private QuizService quizService;

    @RequestMapping(value = "/startQuiz", method = RequestMethod.GET)
    public String startQuiz(@RequestParam(value = "learningGroupId") Integer learningGroupId,
                            Model model) {


        model.addAttribute("newQuiz", new Quiz());
        model.addAttribute("learningGroupId", learningGroupId);

        List<MCQuestion> questions = new ArrayList<>();
        questions.addAll(quizService.getQuestions(userService.getCurrentDesjUser(), learningGroupId));

        model.addAttribute("questions", questions);

        return "StartQuiz";
    }

    @RequestMapping(value = "/submitQuiz", method = RequestMethod.POST)
    public String submitQuiz(@RequestParam(value = "learningGroupId") Integer learningGroupId,
                             Quiz newQuiz) {

        quizService.save(newQuiz, learningGroupRepository.findOne(learningGroupId), userService.getCurrentDesjUser());

        return "redirect:/showStatistics?learningGroupId=" + learningGroupId.toString();
    }
}
