package com.desj.controller;

import com.desj.model.UserRepository;
import com.desj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Julien on 13.04.16.
 * This class controls the login request. If you go to localhost:8080 and aren't logged, spring redirects to /login
 * After authentication you get redirected to
 */
@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDetailsManager userDetailsManager;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "Login";
    }
}
