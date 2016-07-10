package com.desj.service;

import com.desj.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private MCQuestionRepository mcQuestionRepository;

    @Autowired
    private QuestionCommentRepository questionCommentRepository;

    @Autowired
    private QuestionReposiory questionReposiory;

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private GroupPostRepository groupPostRepository;

    public List<User> getAllMemberOfLearningGroup(Integer learningGroupId) {
        return learningGroupRepository.findOne(learningGroupId).getMembers();
    }

    public void delete(Integer learningGroupId) {

        User user = learningGroupRepository.findOne(learningGroupId).getCreatorOfGroup();

        List<MCQuestion> answeredMCQuestionList = new ArrayList<>();
        answeredMCQuestionList.addAll(user.getAnsweredMCQuestions());
        for (MCQuestion mcQuestion : user.getAnsweredMCQuestions()) {
            answeredMCQuestionList.remove(mcQuestion);
        }
        user.setAnsweredMCQuestions(answeredMCQuestionList);

        for (MCQuestion mcQuestion : mcQuestionRepository.findAll()) {
            if (mcQuestion.getCorrespondingLearningGroup().getId() == learningGroupId) {
                mcQuestionRepository.delete(mcQuestion);
            }
        }

        for (QuestionComment questionComment : questionCommentRepository.findAll()) {
            if (questionComment.getCorrespondingQuestion().getCorrespondingLearningGroup().getId() == learningGroupId) {
                questionCommentRepository.delete(questionComment);
            }
        }

        for (Question question : questionReposiory.findAll()) {
            if (question.getCorrespondingLearningGroup().getId().equals(learningGroupId)) {
                questionReposiory.delete(question);
            }
        }

        for (Quiz quiz : quizRepository.findAll()) {
            if (quiz.getLearningGroup().getId().equals(learningGroupId)) {
                quizRepository.delete(quiz);
            }
        }

        for (Comment comment : commentRepository.findAll()) {
            if (comment.getAssociatedGroupPost().getAssociatedLearningGroup().getId() == learningGroupId) {
                commentRepository.delete(comment);
            }
        }

        for (GroupPost groupPost : groupPostRepository.findAll()) {
            if (groupPost.getAssociatedLearningGroup().getId() == learningGroupId) {
                groupPostRepository.delete(groupPost);
            }
        }
        learningGroupRepository.delete(learningGroupRepository.findOne(learningGroupId));
    }

    public void addMemberToLearningGroup(int learningGroupId, User user) {
        List<User> memberList = new ArrayList<>();
        memberList.addAll(getAllMemberOfLearningGroup(learningGroupId));
        memberList.add(user);
        learningGroupRepository.findOne(learningGroupId).setMembers(memberList);
    }

    /**
     * Helps to find all learning groups, which the user isn't a member of yet
     * @return all learning groups, the current user isn't a member of
     */
    public List<LearningGroup> getNewLearningGroups(User user) {

        List<LearningGroup> newLearningGroups = new ArrayList<>();

        for(LearningGroup learningGroup : learningGroupRepository.findAll()) {
            if(!learningGroup.getMembers().contains(user)) {
                newLearningGroups.add(learningGroup);
            }
        }
        return newLearningGroups;
    }

    public List<LearningGroup> getAllLearningGroupsOfUser(User user) {
        List<LearningGroup> learningGroupList = new ArrayList<>();
        for (LearningGroup learningGroup: learningGroupRepository.findAll()) {
            if (learningGroup.getMembers().contains(user)) {
                learningGroupList.add(learningGroup);
            }
        }
        return learningGroupList;
    }

    public List<LearningGroup> getAllLearningGroupsUserHasCreated(User user) {
        List<LearningGroup> learningGroupList = new ArrayList<>();
        for (LearningGroup learningGroup: learningGroupRepository.findAll()) {
            if (learningGroup.getCreatorOfGroup().equals(user)) {
                learningGroupList.add(learningGroup);
            }
        }
        return learningGroupList;
    }



    public void save(LearningGroup learningGroup, User user) {

        if (learningGroup.getPassword() != "") {
            String passwordHash = encoder.encode(learningGroup.getPassword());
            learningGroup.setPassword(passwordHash);
            learningGroup.setPrivate(true);
        }
        else { learningGroup.setPrivate(false); }

        List<User> userList = new ArrayList<>();
        userList.add(user);
        learningGroup.setCreatorOfGroup(user);
        learningGroup.setMembers(userList);
        learningGroupRepository.save(learningGroup);
    }

    public void addFileToLearningGroup(String filename, Integer learningGroupId) {

        List<String> allFiles = new ArrayList<>();
        allFiles.addAll(learningGroupRepository.findOne(learningGroupId).getUploadedFilesList());
        allFiles.add(filename);
        learningGroupRepository.findOne(learningGroupId).setUploadedFilesList(allFiles);
    }
}
