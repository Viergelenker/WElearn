package com.desj.configuration;

import com.desj.model.UserRepository;
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

    private UserRepository userRepository;
    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        com.desj.model.User user1 = new com.desj.model.User();
        user1.setUsername("Julien");
        user1.setEmail("julien@vollweiter.com");
        user1.setMajor("Winfo");
        userRepository.save(user1);

        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

        User adminJulien = new User("Julien", encoder.encode("1234"), authorities);
        userDetailsManager.createUser(adminJulien);

        User desi = new User("desi@mail.com", encoder.encode("1234"), authorities);
        userDetailsManager.createUser(desi);

        // We can still add user manually to the database,
        // but we need to do it in another way because of spring security.
        // As soon as i figured out, i will mention it here

        //added a new user, kept forgetting the other ones details;
        /*
        com.desj.model.User desi = new User();
        desi.setUsername("Desi");
        desi.setMajor("Wirtschaftsinformatik");
        desi.setEmail("desi@mail.com");
        desi.setPassword("1234");
        userRepository.save(desi);
        */
    }
}
