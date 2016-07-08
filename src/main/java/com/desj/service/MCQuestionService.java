package com.desj.service;

import com.desj.model.LearningGroup;
import com.desj.model.MCQuestion;
import com.desj.model.MCQuestionRepository;
import com.desj.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * Created by Desi on 6/16/2016.
 */
@Service
public class MCQuestionService {

    @Autowired
    private MCQuestionRepository mcQuestionRepository;

    Random random = new Random();

    public void save(MCQuestion MCQuestion, LearningGroup learningGroup, User user){
        MCQuestion.setCorrespondingLearningGroup(learningGroup);
        MCQuestion.setCreator(user);

        mcQuestionRepository.save(MCQuestion);

    }
}
