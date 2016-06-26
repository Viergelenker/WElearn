package com.desj.service;

import com.desj.model.LearningGroup;
import com.desj.model.MCQuestion;
import com.desj.model.MCQuestionRepository;
import com.desj.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Desi on 6/16/2016.
 */
@Service
public class MCQuestionService {
    @Autowired
    private MCQuestionRepository MCQuestionRepository;


    public void save(MCQuestion MCQuestion, LearningGroup learningGroup, User user){
        MCQuestion.setCorrespondingLearningGroup(learningGroup);
        MCQuestion.setCreator(user);

        MCQuestionRepository.save(MCQuestion);

    }
}
