package com.desj.controller;

import com.desj.model.UserRepository;
import com.desj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Sabrina on 14.06.2016.
 */
@Controller
public class AboutLogController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @RequestMapping(value = "/aboutLog",method = RequestMethod.POST)
    public String about(Model model){
        model.addAttribute("username", userService.getCurrentDesjUser().getUsername());
        return "AboutLog";
    }
}
