package com.desj.service;

import com.desj.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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


    public void save(Quiz quiz, LearningGroup learningGroup) {

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

    public Quiz getQuestions(User user, Quiz quiz, Integer learningGroupId) {

        List<MCQuestion> questions = quiz.getMCQuestions();
        Random rand = new Random();
        List<MCQuestion> allQuestions = mcQuestionRepository.findAll();
        int q = 0;
        while (q < quiz.getQuestionQuantity() ) {
            Integer randomNumber = rand.nextInt(questions.size());
            MCQuestion question = allQuestions.get(randomNumber);
            if (question.getCorrespondingLearningGroup().getId().equals(learningGroupId)) {
                if (!user.getAnsweredMCQuestions().contains(question)) {
                    questions.add(question);
                    q++;

                }
            }
        }
        quiz.setMCQuestions(questions);
        return quiz;
    }
}
