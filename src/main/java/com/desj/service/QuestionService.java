package com.desj.service;

import com.desj.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public Question getQuestion(LearningGroup learningGroup, User user) {
        Integer iterator = 1;
        List<Question> questionList = questionReposiory.findAll();
        while (iterator <= questionList.size() || questionList.size() != 0) {
            Question question = questionList.get(iterator);
            if (question.getCorrespondingLearningGroup().equals(learningGroup) && !user.getAnsweredQuestions().equals(question)) {
                List<Question> answeredQuestions = user.getAnsweredQuestions();
                answeredQuestions.add(question);
                user.setAnsweredQuestions(answeredQuestions);
                return question;
            } else {
                iterator++;
            }}
        return null;
    }
    public List<Question> getAllQuestionsOfLearningGroup(LearningGroup learningGroup) {
        List<Question> questionList = new ArrayList<>();
        for (Question question : questionReposiory.findAll()) {
            if (question.getCorrespondingLearningGroup().equals(learningGroup)) {
                questionList.add(question);
            }
        }
        return questionList;
    }

    public List<Question> getAllQuestionsOfUser(User user) {
        List<Question> questionList = new ArrayList<>();
        for (Question question : questionReposiory.findAll()) {
            if (question.getCreator().equals(user)) {
                questionList.add(question);
            }
        }
        return questionList;
    }

    public void addQuestionComment(Question question, QuestionComment questionComment) {
        List<QuestionComment> commentList = new ArrayList<>();
        if (question.getComments() != null) {
            commentList.addAll(question.getComments());
        } else {
            commentList.add(questionComment);
            question.setComments(commentList);
        }
    }


}
