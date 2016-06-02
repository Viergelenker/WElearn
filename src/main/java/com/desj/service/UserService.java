package com.desj.service;

import com.desj.model.LearningGroup;
import com.desj.model.User;
import com.desj.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Julien on 25.04.16.
 */
@Service
@Transactional
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

    public List<LearningGroup> getAllLearningGroupsOfUser(int userId) {
        return userRepository.findOne(userId).getLearningGroupsOfUser();
    }

    public void addLearningGroupToUser(LearningGroup learningGroup, int userId) {
        List<LearningGroup> learningGroupList = new ArrayList<>();
        learningGroupList.addAll(getAllLearningGroupsOfUser(userId));
        learningGroupList.add(learningGroup);
        userRepository.findOne(userId).setLearningGroupsOfUser(learningGroupList);
    }

    public void save(User user) {
        userRepository.save(user);
    }
}