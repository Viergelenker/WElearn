package com.desj.service;

import com.desj.model.LearningGroup;
import com.desj.model.MCTest;
import com.desj.model.MCTestRepository;
import com.desj.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Stack;

/**
 * Created by Desi on 6/15/2016.
 */
@Service
@Transactional
public class MCTestService {
    @Autowired
    private MCTestRepository mcTestRepository;

    public void save (MCTest mcTest, User user, LearningGroup learningGroup){
        mcTest.setCreator(user);
        mcTest.setLearningGroup(learningGroup);
        mcTestRepository.save(mcTest);
    }
    public Stack<MCTest> getAllMCTestsOfUser(User user){
        Stack<MCTest> userStack = new Stack<>();
        for (MCTest mcTest:mcTestRepository.findAll()){
            if(mcTest.getCreator().equals(user)){
                userStack.push(mcTest);
            }

        }
        return userStack;
    }
    public Stack<MCTest> getAllMCTestsOfLearningGroup(LearningGroup learningGroup){
        Stack<MCTest> learningGroupStack = new Stack<>();
        for(MCTest mcTest: mcTestRepository.findAll()){
            if(mcTest.getLearningGroup().equals(learningGroup)){
                learningGroupStack.push(mcTest);
            }
        }
        return learningGroupStack;
    }
}
