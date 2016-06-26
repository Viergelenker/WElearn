package com.desj.service;

import com.desj.model.LearningGroup;
import com.desj.model.Quiz;
import com.desj.model.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Stack;

/**
 * Created by Desi on 6/15/2016.
 */
@Service
@Transactional
public class QuizService {
    @Autowired
    private QuizRepository quizRepository;

    public void save (Quiz quiz, LearningGroup learningGroup){

        quiz.setLearningGroup(learningGroup);
        quizRepository.save(quiz);
    }

    public Stack<Quiz> getAllQuizesOfLearningGroup(LearningGroup learningGroup){
        Stack<Quiz> learningGroupStack = new Stack<>();
        for(Quiz quiz : quizRepository.findAll()){
            if(quiz.getLearningGroup().equals(learningGroup)){
                learningGroupStack.push(quiz);
            }
        }
        return learningGroupStack;
    }
}
