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

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private UserRepository userRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        // Set authorities.
        Collection<GrantedAuthority> AdminAuthorities = new ArrayList<>();
        AdminAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

        // Creates new Desj user.
        com.desj.model.User julien = new com.desj.model.User();
        julien.setUsername("Julien");
        julien.setEmail("julien@vollweiter.com");
        julien.setMajor("Winfo");
        userRepository.save(julien);

        com.desj.model.User desi = new com.desj.model.User();
        desi.setUsername("Desi");
        desi.setMajor("Wirtschaftsinformatik");
        desi.setEmail("desi@mail.com");
        desi.setPassword("1234");
        userRepository.save(desi);

        com.desj.model.User robert = new com.desj.model.User();
        robert.setUsername("Robert Rundhals");
        userRepository.save(robert);

        com.desj.model.User friedrich = new com.desj.model.User();
        friedrich.setUsername("Friedrich Fröhlich");
        userRepository.save(friedrich);

        com.desj.model.User leon = new com.desj.model.User();
        leon.setUsername("Leon Lässig");
        userRepository.save(leon);

        // Creates new spring security user. These infos are merged with the Desj user data
        // within the UserServiceImp.java
        User adminJulien = new User("Julien", encoder.encode("1234"), AdminAuthorities);
        userDetailsManager.createUser(adminJulien);

        User adminDesi = new User("desi@mail.com", encoder.encode("1234"), AdminAuthorities);
        userDetailsManager.createUser(adminDesi);
    }
}
