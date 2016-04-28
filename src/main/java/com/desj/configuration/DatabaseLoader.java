package com.desj.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Julien on 23.04.16.
 * This class creates demo content for the test database.
 */
@Component
public class DatabaseLoader implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private UserDetailsManager userDetailsManager;

    @Autowired
    BCryptPasswordEncoder encoder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        Collection<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        User julien = new User("julien@vollweiter.com", encoder.encode("1234"), authorities);

        userDetailsManager.createUser(julien);
    }
}
