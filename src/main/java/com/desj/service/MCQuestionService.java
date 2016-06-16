package com.desj.service;

import com.desj.model.MCTest;
import com.desj.model.MCQuestion;
import com.desj.model.MCQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Desi on 6/16/2016.
 */
@Service
public class MCQuestionService {
    @Autowired
    private MCQuestionRepository MCQuestionRepository;
    public void save(MCQuestion MCQuestion, MCTest mcTest){
        MCQuestion.setCorrespondingMCTest(mcTest);
        MCQuestionRepository.save(MCQuestion);

    }
}
