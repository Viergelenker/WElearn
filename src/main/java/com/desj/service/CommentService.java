package com.desj.service;

import com.desj.model.Comment;
import com.desj.model.CommentRepository;
import com.desj.model.GroupPost;
import com.desj.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    public Map<Integer, Comment> getAllCommentsOfGroupPost(List<GroupPost> allGroupPosts) {

        Map<Integer, Comment> commentMap = new HashMap<>();
        for (GroupPost groupPost1 : allGroupPosts) {

            for (Comment comment : groupPost1.getCommentList()) {

                commentMap.put(groupPost1.getId(), comment);

            }
        }
        return commentMap;
    }
}
