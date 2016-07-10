package com.desj.service;

import com.desj.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Desi on 6/16/2016.
 */
@Service
public class QuestionService {
    @Autowired
    private QuestionReposiory questionReposiory;

    public void save(Question question, LearningGroup learningGroup, User user) {

        question.setCorrespondingLearningGroup(learningGroup);
        question.setCreator(user);
        questionReposiory.save(question);
    }

    public Question getQuestion(LearningGroup learningGroup) {

        List<Question> questionsOfLearningGroup = this.getAllQuestionsOfLearningGroup(learningGroup);

        Question toBeAnswered;

        if (questionsOfLearningGroup.size() != 0) {
            Random rand = new Random();
            Integer iterator = rand.nextInt(questionsOfLearningGroup.size());
            toBeAnswered = questionsOfLearningGroup.get(iterator);
            return toBeAnswered;

        }
        return null;
    }

    private List<Question> getAllQuestionsOfLearningGroup(LearningGroup learningGroup) {
        List<Question> questionList = new ArrayList<>();

        for (Question question : questionReposiory.findAll()) {
            if (question.getCorrespondingLearningGroup().equals(learningGroup)) {
                questionList.add(question);
            }
        }
        return questionList;
    }
}
