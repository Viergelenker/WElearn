package com.desj.controller;

import com.desj.model.User;
import com.desj.model.UserRepository;
import com.desj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

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
    UserDetailsManager userDetailsManager;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {

        List<String> usernames = new ArrayList<>();
        for (User user1 : userRepository.findAll()) {
            usernames.add(user1.getUsername());
        }
        model.addAttribute("usernames", usernames);

        List<String> emails = new ArrayList<>();
        for (User user2 : userRepository.findAll()) {
            emails.add(user2.getEmail());
        }
        model.addAttribute("emails", emails);

        model.addAttribute("user", new User());

        return "Login";
    }

    @RequestMapping(value = "/newUser", method = RequestMethod.POST)
    public String createNewUser(@Valid User user, BindingResult bindingResult) {


        if (bindingResult.hasErrors()) {
            return "Login";
        } else {
            userService.save(user);

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetailsManager
                    .loadUserByUsername(user.getEmail()), null, userDetailsManager.loadUserByUsername(user.getEmail()).getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return "redirect:/";
        }

    }
}
