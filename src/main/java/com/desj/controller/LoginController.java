package com.desj.controller;

import com.desj.model.User;
import com.desj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by Julien on 13.04.16.
 * This class controls the login request. If you go to localhost:8080 and aren't logged, spring redirects to /login
 * After authentication you get redirected to
 */
@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "Login";
    }

    @RequestMapping(value = "/newUser", method = RequestMethod.POST)
    public String createNewUser(@Valid User user, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "Login";
        } else {
            userService.save(user);
            return "redirect:/signUpSuccess?userId=" + user.getId().toString();
        }
    }
}
