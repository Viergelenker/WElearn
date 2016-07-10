package com.desj.service;

import com.desj.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Julien on 10.06.16.
 */
@Service
public class GroupPostService {

    @Autowired
    private GroupPostRepository groupPostRepository;

    @Autowired
    private CommentRepository commentRepository;

    public void delete(Integer groupPostId) {
        for (Comment comment : commentRepository.findAll()) {
            if (comment.getAssociatedGroupPost().getId() == groupPostId) {
                commentRepository.delete(comment);
            }
        }

        groupPostRepository.delete(groupPostRepository.findOne(groupPostId));
    }

    public void save(GroupPost groupPost, User user, LearningGroup learningGroup) {

        groupPost.setAssociatedUser(user);
        groupPost.setAssociatedLearningGroup(learningGroup);
        groupPostRepository.save(groupPost);
    }

    public List<GroupPost> getAllGroupPostsOfLearningGroup(LearningGroup learningGroup) {
        List<GroupPost> groupPosts = new ArrayList<>();
        for(GroupPost groupPost : groupPostRepository.findAll()) {
            if(groupPost.getAssociatedLearningGroup().equals(learningGroup)) {
                groupPosts.add(groupPost);
            }
        }
        Collections.reverse(groupPosts);
        return groupPosts;
    }
}
