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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Julien on 01.06.16.
 */
@Controller
public class SignUpController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDetailsManager userDetailsManager;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @RequestMapping("/signUp")
    public String signUp(Model model) {
        model.addAttribute("user", new User());
        return "SignUp";
    }

    @RequestMapping(value = "newUser", method = RequestMethod.POST)
    public String saveUser(User user) {

        Collection<GrantedAuthority> userAuthorities = new ArrayList<>();
        userAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        org.springframework.security.core.userdetails.User newUser =
                new org.springframework.security.core.userdetails.User(
                        user.getEmail(), encoder.encode(user.getPassword()), userAuthorities);
        userDetailsManager.createUser(newUser);
        user.setPassword(newUser.getPassword());
        userRepository.save(user);
        return "redirect:/Login";
    }
}
