package com.desj.service;

import com.desj.model.LearningGroup;
import com.desj.model.Test;
import com.desj.model.TestRepository;
import com.desj.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Desi on 6/16/2016.
 */
@Service
public class TestService {
    @Autowired
    private TestRepository testRepository;

    public void save(Test test, User user, LearningGroup learningGroup){
        test.setCreator(user);
        test.setAssociatedLearningGroup(learningGroup);
        testRepository.save(test);

    }

    public List<Test> getAllTestsOfLearningGroup(LearningGroup learningGroup){
        List<Test> tests = new ArrayList<>();
        for(Test test: testRepository.findAll()){
            if (test.getAssociatedLearningGroup().equals(learningGroup)){
                tests.add(test);
            }
        }
        return tests;
    }
    public List<Test> getAllTestsOfUser(User user){
        List<Test> tests = new ArrayList<>();
        for (Test test: testRepository.findAll()){
            if(test.getCreator().equals(user)){
                tests.add(test);
            }
        }
        return tests;

    }
}
