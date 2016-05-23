package com.desj.controller;

import com.desj.model.User;
import com.desj.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Desi on 5/23/2016.
 */
@Controller
public class SignUpController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/signUp", method = RequestMethod.GET)
    public String signUp() {
        return "SignUp";
    }

    @RequestMapping(value = "newUser", method = RequestMethod.POST)
    public String saveUser(User user) {
        userRepository.save(user);
        return "redirect:/login";
    }
}
