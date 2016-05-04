package com.desj.configuration;

import com.desj.model.User;
import com.desj.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Created by Julien on 23.04.16.
 * This class creates demo content for the test database.
 */
@Component
public class DatabaseLoader implements ApplicationListener<ContextRefreshedEvent> {

    private UserRepository userRepository;

    @Autowired

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        User julien = new User();
        julien.setEmail("julien@vollweiter.com");
        julien.setUsername("Julien");
        julien.setPassword("1234");
        //needed a major and jura is the coolest;
        julien.setMajor("Jura");
        userRepository.save(julien);
        //added a new user, kept forgetting the other ones details;
        User desi = new User();
        desi.setUsername("Desi");
        desi.setMajor("Wirtschaftsinformatik");
        desi.setEmail("desi@mail.com");
        desi.setPassword("1234");
        userRepository.save(desi);
    }
}
