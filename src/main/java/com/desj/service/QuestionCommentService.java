package com.desj.service;

import com.desj.model.Question;
import com.desj.model.QuestionComment;
import com.desj.model.QuestionCommentRepository;
import com.desj.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Desi on 6/26/2016.
 */
@Service
public class QuestionCommentService {
    @Autowired
    private QuestionCommentRepository questionCommentRepository;

    public List<QuestionComment> getAllCommentsOfQuestion(Question question){
        List<QuestionComment> commentList = new ArrayList<>();
        for (QuestionComment questionComment: questionCommentRepository.findAll()){
            if(questionComment.getCorrespondingQuestion().equals(question)){
                commentList.add(questionComment);
            }
        }
        System.out.println(commentList.size());
        return commentList;
    }



    public void save(QuestionComment questionComment, User user, Question question){
        questionComment.setCreator(user);
        questionComment.setCorrespondingQuestion(question);
        questionCommentRepository.save(questionComment);
    }
}
