package com.desj.service;

import com.desj.model.Comment;
import com.desj.model.CommentRepository;
import com.desj.model.GroupPost;
import com.desj.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by Sabrina on 14.06.2016.
 */
@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public void save(Comment comment, User user, GroupPost groupPost){
        comment.setAssociatedGroupPost(groupPost);
        comment.setCreator(user);
        commentRepository.save(comment);
    }
    public List<Comment> getAllCommentsOfGroupPost(GroupPost groupPost){
        List<Comment> comments = new ArrayList<>();
        for(Comment comment: commentRepository.findAll()){
            if(comment.getAssociatedGroupPost().equals(groupPost)){
                comments.add(comment);
            }
        }
        Collections.reverse(comments);
        return comments;
    }
}
