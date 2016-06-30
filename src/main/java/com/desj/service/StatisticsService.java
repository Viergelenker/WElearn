package com.desj.service;

import com.desj.model.LearningGroup;
import com.desj.model.LearningGroupRepository;
import com.desj.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Julien on 01.07.16.
 */
@Service
public class StatisticsService {

    @Autowired
    private UserService userService;

    @Autowired
    private LearningGroupRepository learningGroupRepository;

    public List<Integer> getTotalPointsOfAllMembersChartReady(LearningGroup learningGroup) {

        List<Integer> allPoints = new ArrayList<>();

        for (User user : learningGroupRepository.findOne(learningGroup.getId()).getMembers()) {
            allPoints.add(userService.getTotalOfQuizPointsForUserForLearningGroup(user, learningGroup));
        }
        return allPoints;
    }

    public List<String> getAllUsernamesOfMembersChartReady(LearningGroup learningGroup) {

        List<String> usernames = new ArrayList<>();

        for (User user : learningGroupRepository.findOne(learningGroup.getId()).getMembers()) {
            usernames.add(user.getUsername());
        }
        return usernames;
    }

    public List<Double> getAllAveragePointsOfMembersChartReady(LearningGroup learningGroup) {

        List<Double> allAveragePoints = new ArrayList<>();

        for (User user : learningGroupRepository.findOne(learningGroup.getId()).getMembers()) {
            allAveragePoints.add(userService.calculateAveragePoints(user, learningGroup));
        }
        return allAveragePoints;
    }
}
