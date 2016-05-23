package com.desj.controller;

import com.desj.service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Julien on 25.04.16.
 */
@Controller
public class WelcomeController {

    @Autowired
    UserServiceImp userServiceImp;

    @Autowired
    UserDetailsService userDetailsService;

    @RequestMapping("/")
    public String welcomeUser(Model model) {
        model.addAttribute("username", userServiceImp.getCurrentDesjUser().getUsername());
        model.addAttribute("allUser", userServiceImp.getAllDesjUser());
        return "welcome";
    }


}
