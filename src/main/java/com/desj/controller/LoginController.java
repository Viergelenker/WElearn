package com.desj.controller;

import com.desj.model.User;
import com.desj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Julien on 13.04.16.
 */
@Controller
public class LoginController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @RequestMapping(value = "newUser", method = RequestMethod.POST)
    public String saveUser(User user){
        userService.saveUser(user);
        return "redirect:/welcome/" + user.getId();
    }

}
