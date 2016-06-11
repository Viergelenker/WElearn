package com.desj.controller;

import com.desj.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Julien on 01.06.16.
 */
@Controller
public class SignUpSuccessController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/signUpSuccess{userId}")
    public String signUpSuccess(@RequestParam(value = "userId") Integer userId, Model model) {
        model.addAttribute("user", userRepository.findOne(userId));
        return "SignUpSuccess";
    }
}
