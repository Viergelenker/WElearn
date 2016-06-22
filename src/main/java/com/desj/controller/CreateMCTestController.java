package com.desj.controller;

import com.desj.model.Quiz;
import com.desj.model.QuizRepository;
import com.desj.service.QuizService;
import com.desj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Desi on 6/15/2016.
 */
@Controller
public class CreateMCTestController {
    @Autowired
    private UserService userService;
    @Autowired
    private QuizRepository quizRepository;
    @Autowired
    private QuizService quizService;


    @RequestMapping(value = "/createMCTest{id}", method = RequestMethod.GET)
    public String showCreateMCTest(@RequestParam(value = "id", required = false) Integer id, Model model) {
        model.addAttribute("ussername", userService.getCurrentDesjUser().getId());
        model.addAttribute("newlyCreatedMCTestId", id);
        if (id != null) {
            model.addAttribute("createdMCTest", quizRepository.findOne(id));
        }
        model.addAttribute("mcTest", new Quiz());
        return "CreateMCTest";
    }
//warning !!!! null value!!
    @RequestMapping(value = "/createNewMCTest", method = RequestMethod.POST)
    public String createNewMCTest(@ModelAttribute("createdMCTest") Quiz quiz) {
        quizService.save(quiz, userService.getCurrentDesjUser(), null);
        return "redirect://createMCTest?id=" + quiz.getId().toString();
    }
}
