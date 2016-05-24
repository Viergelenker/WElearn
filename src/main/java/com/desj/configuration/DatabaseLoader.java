package com.desj.configuration;

import com.desj.model.LearningGroup;
import com.desj.model.LearningGroupRepository;
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
import java.util.List;

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
    private UserRepository userRepository;

    @Autowired
    private LearningGroupRepository learningGroupRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        // Creates new Desj user.
        com.desj.model.User julien = new com.desj.model.User();
        julien.setUsername("Julien");
        julien.setEmail("julien@vollweiter.com");
        julien.setMajor("Winfo");
        userRepository.save(julien);

        com.desj.model.User desi = new com.desj.model.User();
        desi.setUsername("desi");
        desi.setMajor("Wirtschaftsinformatik");
        desi.setEmail("desi@mail.com");
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

        // Set authorities.
        Collection<GrantedAuthority> AdminAuthorities = new ArrayList<>();
        AdminAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

        Collection<GrantedAuthority> UserAuthorities = new ArrayList<>();
        UserAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        // Creates new spring security user. These infos are merged with the Desj user data
        // within the UserService.java
        User adminJulien = new User("julien@vollweiter.com", encoder.encode("1234"), AdminAuthorities);
        userDetailsManager.createUser(adminJulien);

        User adminDesi = new User("desi@mail.com", encoder.encode("1234"), AdminAuthorities);
        userDetailsManager.createUser(adminDesi);

        User userRobert = new User("robert@rundhals.com", encoder.encode("1234"), UserAuthorities);
        userDetailsManager.createUser(userRobert);

        // New learning groups
        List<com.desj.model.User> userList = new ArrayList<>();
        LearningGroup group1 = new LearningGroup();
        group1.setName("Mathe Meister");
        group1.setSubject("Mathe");
        userList.add(julien);
        userList.add(desi);
        group1.setMembers(userList);
        learningGroupRepository.save(group1);

        LearningGroup group2 = new LearningGroup();
        group2.setName("Statistik Streber");
        group2.setSubject("Statistik");
        userList.add(robert);
        userList.add(friedrich);
        group2.setMembers(userList);
        learningGroupRepository.save(group2);
    }
}
