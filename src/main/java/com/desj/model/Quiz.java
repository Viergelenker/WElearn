package com.desj.model;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Desi on 6/15/2016.
 */
@Entity
@Component
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne
    @JoinColumn(name="USER_ID")
    private User quizParticipant;

    @OneToOne
    @JoinColumn(name = "learningGroupId")
    private LearningGroup learningGroup;

    @ManyToMany
    @JoinTable(
            name = "QUIZQUESTION",
            joinColumns = @JoinColumn(name = "QUIZ_ID"),
            inverseJoinColumns = @JoinColumn(name = "MCQUESTION_ID"))
    private List<MCQuestion> mcQuestions = new ArrayList<>();

    private String questionIds;

    private Integer pointsForCorrectAnswers;

    private String givenAnswers_0;
    private String givenAnswers_1;
    private String givenAnswers_2;
    private String givenAnswers_3;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getQuizParticipant() {
        return quizParticipant;
    }

    public void setQuizParticipant(User quizParticipant) {
        this.quizParticipant = quizParticipant;
    }

    public LearningGroup getLearningGroup() {
        return learningGroup;
    }

    public void setLearningGroup(LearningGroup learningGroup) {
        this.learningGroup = learningGroup;
    }

    public List<MCQuestion> getMcQuestions() {
        return mcQuestions;
    }

    public void setMcQuestions(List<MCQuestion> mcQuestions) {
        this.mcQuestions = mcQuestions;
    }

    public String getQuestionIds() {
        return questionIds;
    }

    public void setQuestionIds(String questionIds) {
        this.questionIds = questionIds;
    }

    public Integer getPointsForCorrectAnswers() {
        return pointsForCorrectAnswers;
    }

    public void setPointsForCorrectAnswers(Integer pointsForCorrectAnswers) {
        this.pointsForCorrectAnswers = pointsForCorrectAnswers;
    }

    public String getGivenAnswers_0() {
        return givenAnswers_0;
    }

    public void setGivenAnswers_0(String givenAnswers_0) {
        this.givenAnswers_0 = givenAnswers_0;
    }

    public String getGivenAnswers_1() {
        return givenAnswers_1;
    }

    public void setGivenAnswers_1(String givenAnswers_1) {
        this.givenAnswers_1 = givenAnswers_1;
    }

    public String getGivenAnswers_2() {
        return givenAnswers_2;
    }

    public void setGivenAnswers_2(String givenAnswers_2) {
        this.givenAnswers_2 = givenAnswers_2;
    }

    public String getGivenAnswers_3() {
        return givenAnswers_3;
    }

    public void setGivenAnswers_3(String givenAnswers_3) {
        this.givenAnswers_3 = givenAnswers_3;
    }
}

