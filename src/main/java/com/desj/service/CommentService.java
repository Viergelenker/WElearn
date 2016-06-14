package com.desj.service;

import com.desj.model.Comment;
import com.desj.model.CommentRepository;
import com.desj.model.GroupPost;
import com.desj.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Sabrina on 14.06.2016.
 */
@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public void save(Comment comment, User user, GroupPost groupPost) {
        comment.setAssociatedGroupPost(groupPost);
        comment.setCreator(user);
        commentRepository.save(comment);
    }
}
