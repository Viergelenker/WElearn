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
        List<Question> createdQuestions = new ArrayList<Question>();
        createdQuestions.addAll(user.getCreatedQuestions());

        createdQuestions.add(question);

        user.setCreatedQuestions(createdQuestions);


    }


    public Question getQuestion(LearningGroup learningGroup, User user){



        List<Question> questionsOfLearningGroup = this.getAllQuestionsOfLearningGroup(learningGroup);

        Question toBeAnswered;

        if (questionsOfLearningGroup.size()!=0){
            Random rand = new Random();
            Integer iterator =rand.nextInt(questionsOfLearningGroup.size());
            System.out.println(iterator);
            toBeAnswered=questionsOfLearningGroup.get(iterator);
            List<Question> answeredQuestions = new ArrayList<>();
            answeredQuestions.addAll(user.getAnsweredQuestions());
            answeredQuestions.add(toBeAnswered);
            user.setAnsweredQuestions(answeredQuestions);
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



    public void addQuestionComment(Question question, QuestionComment questionComment) {
        List<QuestionComment> commentList = new ArrayList<>();
        if (question.getComments() != null) {
            commentList.addAll(question.getComments());

        } else {
            commentList.add(questionComment);
            question.setComments(commentList);
        }
    }
     public boolean questionSeen(Question question, User user){
         if(user.getAnsweredQuestions().contains(question)){
             return true;
         }
         return false;
     }


}
