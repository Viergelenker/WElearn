package com.desj.service;

import com.desj.model.LearningGroup;
import com.desj.model.Question;
import com.desj.model.QuestionReposiory;
import com.desj.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Desi on 6/16/2016.
 */
@Service
public class QuestionService {
    @Autowired
    private QuestionReposiory questionReposiory;


    public void save(Question question, LearningGroup learningGroup, User user){
        question.setCorrespondingLearningGroup(learningGroup);
        question.setCreator(user);
        questionReposiory.save(question);

    }
}
