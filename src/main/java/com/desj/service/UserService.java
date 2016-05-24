package com.desj.service;

import com.desj.model.User;
import com.desj.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * Created by Julien on 25.04.16.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getCurrentDesjUser() {
        return getUserByUsername(((org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal()).getUsername());
    }

    public User getUserByUsername(String email) {
        return userRepository.findUserByEmail(email);
    }
}