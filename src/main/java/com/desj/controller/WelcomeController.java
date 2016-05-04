package com.desj.controller;

import com.desj.service.UserService;
import com.desj.service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Julien on 25.04.16.
 */
@Controller
public class WelcomeController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    UserServiceImp userServiceImp;

    @Autowired
    UserDetailsService userDetailsService;

    @RequestMapping("/")
    public String welcomeUser() {
        return "welcome";
    }


}
