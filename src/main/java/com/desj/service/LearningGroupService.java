package com.desj.service;

import com.desj.model.LearningGroupRepository;
import com.desj.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Julien on 23.05.16.
 */
@Service
@Transactional
public class LearningGroupService {

    @Autowired
    LearningGroupRepository learningGroupRepository;

    public List<User> getAllMemberOfLearningGroup(int learningGroupId) {
        return learningGroupRepository.findOne(learningGroupId).getMembers();
    }

    public void addMemberToLearningGroup(int learningGroupId, User user) {
        List<User> memberList = new ArrayList<>();
        memberList.addAll(getAllMemberOfLearningGroup(learningGroupId));
        memberList.add(user);
        learningGroupRepository.findOne(learningGroupId).setMembers(memberList);
    }

}
