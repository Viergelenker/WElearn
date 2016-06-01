package com.desj.controller;

import com.desj.model.User;
import com.desj.model.UserRepository;
import com.desj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.Collection;

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
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "Login";
    }

    @RequestMapping(value = "/newUser", method = RequestMethod.POST)
    public String createNewUser(@ModelAttribute("user")User user) {

        // TODO: This is ugly, we need to put this into a service class!
        Collection<GrantedAuthority> userAuthorities = new ArrayList<>();
        userAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        org.springframework.security.core.userdetails.User newUser =
                new org.springframework.security.core.userdetails.User(
                        user.getEmail(), encoder.encode(user.getPassword()), userAuthorities);
        userDetailsManager.createUser(newUser);
        user.setPassword(newUser.getPassword());
        userRepository.save(user);

        return "redirect: /login";
    }
}
