package com.desj.service;

import com.desj.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Julien on 25.04.16.
 */
@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private UserDetailsManager userDetailsManager;

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private GroupPostRepository groupPostRepository;

    @Autowired
    private MCQuestionRepository mcQuestionRepository;

    @Autowired
    private QuestionCommentRepository questionCommentRepository;

    @Autowired
    private QuestionReposiory questionReposiory;

    @Autowired
    private LearningGroupRepository learningGroupRepository;

    @Autowired
    private LearningGroupService learningGroupService;

    public void delete(User user) {

        for (Comment comment : commentRepository.findAll()) {
            if (comment.getCreator().equals(user)) {
                commentRepository.delete(comment);
            }
        }

        for (GroupPost groupPost : groupPostRepository.findAll()) {
            if (groupPost.getAssociatedUser().equals(user)) {
                groupPostRepository.delete(groupPost);
            }
        }

        List<MCQuestion> answeredMCQuestionList = new ArrayList<>();
        answeredMCQuestionList.addAll(user.getAnsweredMCQuestions());
        for (MCQuestion mcQuestion : user.getAnsweredMCQuestions()) {
            answeredMCQuestionList.remove(mcQuestion);
        }
        user.setAnsweredMCQuestions(answeredMCQuestionList);

        for (MCQuestion mcQuestion : mcQuestionRepository.findAll()) {
            if (mcQuestion.getCreator().equals(user)) {
                mcQuestionRepository.delete(mcQuestion);
            }
        }

        for (QuestionComment questionComment : questionCommentRepository.findAll()) {
            if (questionComment.getCreator().equals(user)) {
                questionCommentRepository.delete(questionComment);
            }
        }

        for (Question question : questionReposiory.findAll()) {
            if (question.getCreator().equals(user)) {
                questionReposiory.delete(question);
            }
        }

        for (Quiz quiz : quizRepository.findAll()) {
            if (quiz.getQuizParticipant().equals(user)) {
                quizRepository.delete(quiz);
            }
        }

        for (LearningGroup learningGroup : learningGroupRepository.findAll()) {
            if (learningGroup.getCreatorOfGroup().equals(user)) {
                learningGroupService.delete(learningGroup.getId());
            }
        }

        for (LearningGroup learningGroup : learningGroupRepository.findAll()) {
            if (learningGroup.getMembers().contains(user)) {
                List<User> userList = new ArrayList<>();
                userList.addAll(learningGroup.getMembers());
                userList.remove(user);
                learningGroup.setMembers(userList);
            }
        }

        String userEmail = user.getEmail();
        userRepository.delete(user);
        userDetailsManager.deleteUser(userEmail);
    }

    public void save(User user) {

        Collection<GrantedAuthority> userAuthorities = new ArrayList<>();
        userAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        org.springframework.security.core.userdetails.User newSpringUser =
                new org.springframework.security.core.userdetails.User(
                        user.getEmail(), encoder.encode(user.getPassword()), userAuthorities);
        userDetailsManager.createUser(newSpringUser);

        user.setPassword(newSpringUser.getPassword());
        userRepository.save(user);
    }

    public User getCurrentDesjUser() {
        return getUserByUsername(((org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal()).getUsername());
    }

    public User getUserByUsername(String email) {
        return userRepository.findUserByEmail(email);
    }

    public List<Quiz> getAllQuizOfUser(User user) {

        List<Quiz> quizList = new ArrayList<>();

        for (Quiz quiz : quizRepository.findAll()) {
            if (quiz.getQuizParticipant().equals(user)) {
                quizList.add(quiz);
            }
        }
        return quizList;
    }

    public List<Quiz> getAllQuizzesOfUserInLearningGroup(User user, LearningGroup learningGroup) {

        List<Quiz> quizList = new ArrayList<>();

        for (Quiz quiz : getAllQuizOfUser(user)) {
            if (quiz.getLearningGroup().equals(learningGroup)) {
                quizList.add(quiz);
            }
        }
        return quizList;
    }

    public Integer getTotalOfQuizPointsForUserForLearningGroup(User user, LearningGroup learningGroup) {

        int sumOfPoints = 0;

        for (Quiz quiz : getAllQuizOfUser(user)) {
            if (quiz.getLearningGroup().equals(learningGroup)) {
                sumOfPoints = sumOfPoints + quiz.getPointsForCorrectAnswers();
            }
        }
        return sumOfPoints;
    }

    public Double calculateAveragePoints(User user, LearningGroup learningGroup) {

        double average;

        if (getTotalOfQuizPointsForUserForLearningGroup(user, learningGroup) == 0) {
            return 0.0;
        }
        else {
            average = getTotalOfQuizPointsForUserForLearningGroup(user, learningGroup);
            average = average / Double.valueOf(getAllQuizzesOfUserInLearningGroup(user, learningGroup).size());
            return  average;
        }
    }
}