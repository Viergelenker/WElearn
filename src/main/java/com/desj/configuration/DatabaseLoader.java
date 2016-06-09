package com.desj.configuration;

import com.desj.model.LearningGroup;
import com.desj.model.LearningGroupRepository;
import com.desj.model.UserRepository;
import com.desj.service.LearningGroupService;
import com.desj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Julien on 23.04.16.
 * This class creates demo content for the test database.
 */
@Configuration
@ComponentScan(basePackages = "com.desj")
@Transactional
public class DatabaseLoader implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private UserDetailsManager userDetailsManager;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private LearningGroupRepository learningGroupRepository;

    @Autowired
    private LearningGroupService learningGroupService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        // New learning groups
        // List<com.desj.model.User> userList = new ArrayList<>();
        LearningGroup group1 = new LearningGroup();
        group1.setName("Mathe Meister");
        group1.setSubject("Mathe");
        // userList.add(julien);
        // userList.add(desi);
        // group1.setMembers(userList);
        learningGroupRepository.save(group1);

        LearningGroup group2 = new LearningGroup();
        group2.setName("Statistik Streber");
        group2.setSubject("Statistik");
        // userList.add(robert);
        // userList.add(friedrich);
        // group2.setMembers(userList);
        learningGroupRepository.save(group2);

        // Creates new Desj user.

        // Julien
        com.desj.model.User julien = new com.desj.model.User();
        julien.setUsername("Julien");
        julien.setEmail("julien@vollweiter.com");
        julien.setMajor("Winfo");
        userRepository.save(julien);
        // Add learning groups to the user class...
        userService.addLearningGroupToUser(group1, julien.getId());
        userService.addLearningGroupToUser(group2, julien.getId());
        // ... and add the user to the learning group class
        learningGroupService.addMemberToLearningGroup(group1.getId(), julien);
        learningGroupService.addMemberToLearningGroup(group2.getId(), julien);


        // Desi
        com.desj.model.User desi = new com.desj.model.User();
        desi.setUsername("Desi");
        desi.setMajor("Wirtschaftsinformatik");
        desi.setEmail("desi@mail.com");
        userRepository.save(desi);
        // Add learning groups to the user class...
        userService.addLearningGroupToUser(group1, desi.getId());
        userService.addLearningGroupToUser(group2, desi.getId());
        // ... and add the user to the learning group class
        learningGroupService.addMemberToLearningGroup(group1.getId(), desi);
        learningGroupService.addMemberToLearningGroup(group2.getId(), desi);

        // Sabrina
        com.desj.model.User sabrina = new com.desj.model.User();
        sabrina.setUsername("Sabrina");
        sabrina.setMajor("Winfo");
        sabrina.setEmail("sabrina@mail.com");
        userRepository.save(sabrina);

        // Robert
        com.desj.model.User robert = new com.desj.model.User();
        robert.setUsername("Robert Rundhals");
        userRepository.save(robert);
        learningGroupService.addMemberToLearningGroup(group1.getId(), robert);

        // Friedrich
        com.desj.model.User friedrich = new com.desj.model.User();
        friedrich.setUsername("Friedrich Fröhlich");
        userRepository.save(friedrich);

        // Leon
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

        User adminSabrina = new User("sabrina@mail.com", encoder.encode("1234"), AdminAuthorities);
        userDetailsManager.createUser(adminSabrina);

        User userRobert = new User("robert@rundhals.com", encoder.encode("1234"), UserAuthorities);
        userDetailsManager.createUser(userRobert);
    }
}
