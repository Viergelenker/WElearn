package com.desj.service;

import com.desj.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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

    public void save(Quiz quiz, LearningGroup learningGroup, User user) {

        List<String> givenAnswersList = new ArrayList<>();
        givenAnswersList.add(quiz.getGivenAnswers_0());
        givenAnswersList.add(quiz.getGivenAnswers_1());
        givenAnswersList.add(quiz.getGivenAnswers_2());
        givenAnswersList.add(quiz.getGivenAnswers_3());

        List<MCQuestion> mcQuestions = new ArrayList<>();
        MCQuestion mcQuestion;
        int points = 0;

        List<Integer> questionIds = new ArrayList<>();

        for (int j = 0; j < quiz.getQuestionIds().length(); j++) {
            if (quiz.getQuestionIds().charAt(j) != ',') {
                questionIds.add(Character.getNumericValue(quiz.getQuestionIds().charAt(j)));
            }
        }

        for (int i = 0; i < 4; i++) {

            mcQuestion = mcQuestionRepository.findOne(questionIds.get(i));
            mcQuestions.add(mcQuestion);
            if (mcQuestion.getCorrectAnswers().equals(givenAnswersList.get(i))) {
                points++;
            }
            // else points--; if we decide to give penalty points for wrong answers
        }
        user.setAnsweredMCQuestions(mcQuestions);
        quiz.setQuizParticipant(user);
        quiz.setPointsForCorrectAnswers(points);
        quiz.setMcQuestions(mcQuestions);
        quiz.setLearningGroup(learningGroup);
        quizRepository.save(quiz);
    }

    public Stack<Quiz> getAllQuizesOfLearningGroup(LearningGroup learningGroup) {
        Stack<Quiz> quizStack = new Stack<>();
        for (Quiz quiz : quizRepository.findAll()) {
            if (quiz.getLearningGroup().equals(learningGroup)) {
                quizStack.push(quiz);
            }
        }
        return quizStack;
    }

    public List<MCQuestion> getQuestions(User user, Integer learningGroupId) {

        List<MCQuestion> mcQuestions = new ArrayList<>();
        Integer iterator = 1;
        MCQuestion mcQuestion;

        while (iterator < mcQuestionRepository.findAll().size() +1 && mcQuestions.size() < 5) {

            mcQuestion = mcQuestionRepository.findOne(iterator);

            if (mcQuestion.getCorrespondingLearningGroup().getId().equals(learningGroupId)
                    && !user.getAnsweredMCQuestions().contains(mcQuestion)) {
                mcQuestions.add(mcQuestion);
            }
            iterator++;
        }

        return mcQuestions;
    }
}
