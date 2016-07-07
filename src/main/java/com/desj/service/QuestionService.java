package com.desj.service;

import com.desj.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

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

    /*public Question getQuestion(LearningGroup learningGroup, User user) {
        Integer iterator = 0;
        List<Question> questionList = questionReposiory.findAll();
        while (iterator < questionList.size() && questionList.size() != 0) {
            Question question = questionList.get(iterator);
            if (question.getCorrespondingLearningGroup().equals(learningGroup) && !user.getAnsweredQuestions().contains(question)) {

                List<Question> answeredQuestions = new ArrayList<>();
                if (user.getAnsweredQuestions() != null) {

                    answeredQuestions.addAll(user.getAnsweredQuestions());
                    answeredQuestions.add(question);
                    user.setAnsweredQuestions(answeredQuestions);
                } else {
                    answeredQuestions.add(question);
                    user.setAnsweredQuestions(answeredQuestions);
                }
                return question;
            } else {
                iterator++;
            }
        }
        return null;
    }*/

    public Question getQuestion(LearningGroup learningGroup, User user){
        Integer iterator =0;
        List<Question> questionsOfLearningGroup = this.getAllQuestionsOfLearningGroup(learningGroup);
        Question toBeAnswered;
        while(iterator<questionsOfLearningGroup.size()&&questionsOfLearningGroup.size()!=0&&iterator!=-1){
            toBeAnswered=questionsOfLearningGroup.get(iterator);
            if(!user.getAnsweredQuestions().contains(toBeAnswered)){
                List<Question> answeredQuestions = new ArrayList<>();
                answeredQuestions.addAll(user.getAnsweredQuestions());
                answeredQuestions.add(toBeAnswered);
                user.setAnsweredQuestions(answeredQuestions);
                iterator=-1;
            }else{
                iterator++;
            }
        }
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
