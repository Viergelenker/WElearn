package com.desj.service;

import com.desj.model.GroupPost;
import com.desj.model.GroupPostRepository;
import com.desj.model.LearningGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Julien on 10.06.16.
 */
@Service
public class GroupPostService {

    @Autowired
    private GroupPostRepository groupPostRepository;

    public List<GroupPost> getAllGroupPostsOfLearningGroup(LearningGroup learningGroup) {
        List<GroupPost> groupPosts = new ArrayList<>();
        for(GroupPost groupPost : groupPostRepository.findAll()) {
            if(groupPost.getAssociatedLearningGroup().equals(learningGroup)) {
                groupPosts.add(groupPost);
            }
        }
        return groupPosts;
    }
}
