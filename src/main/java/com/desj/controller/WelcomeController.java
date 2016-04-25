package com.desj.controller;

import com.desj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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

    @RequestMapping("/welcome/{id}")
    public String welcomeUser(@PathVariable Integer id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "welcome";
    }


}
