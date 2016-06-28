package com.desj.service;

import com.desj.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

/**
 * Created by Desi on 6/15/2016.
 */
@Service
@Transactional
public class QuizService {
    @Autowired
    private QuizRepository quizRepository;
    @Autowired
    private MCQuestionRepository mcQuestionRepository;
    @Autowired
    private LearningGroupRepository learningGroupRepository;


    public void save(User user, Quiz quiz, LearningGroup learningGroup) {

        quiz.setLearningGroup(learningGroup);
        quizRepository.save(quiz);
    }

    public Stack<Quiz> getAllQuizesOfLearningGroup(LearningGroup learningGroup) {
        Stack<Quiz> learningGroupStack = new Stack<>();
        for (Quiz quiz : quizRepository.findAll()) {
            if (quiz.getLearningGroup().equals(learningGroup)) {
                learningGroupStack.push(quiz);
            }
        }
        return learningGroupStack;
    }

    public List<MCQuestion> getQuestions(User user, Integer learningGroupId, Integer questionQuantity) {

        List<MCQuestion> questions = new ArrayList<>();

        Random random = new Random();
        List<MCQuestion> allQuestions = mcQuestionRepository.findAll();
        int q = 0;
        while (q < questionQuantity ) {
            Integer randomNumber = random.nextInt(allQuestions.size());
            MCQuestion question = allQuestions.get(randomNumber);
            if (question.getCorrespondingLearningGroup().getId().equals(learningGroupId)) {
                if (!user.getAnsweredMCQuestions().contains(question)) {
                    questions.add(question);
                    q++;

                }
            }
        }

        return questions;
    }
}
