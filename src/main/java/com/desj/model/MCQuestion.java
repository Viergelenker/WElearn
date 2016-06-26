package com.desj.model;

import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 * Created by Desi on 6/15/2016.
 */
@Entity
@Component
public class MCQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Lob
    private String question;



    @Lob
    private String correctAnswer;


    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name ="GROUP_ID")
    private LearningGroup correspondingLearningGroup;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "CREATOR_ID")
    private User creator;

    @Lob
    private String answerA;
    @Lob
    private String answerB;
    @Lob
    private String answerC;
    @Lob
    private String answerD;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public LearningGroup getCorrespondingLearningGroup() {
        return correspondingLearningGroup;
    }

    public void setCorrespondingLearningGroup(LearningGroup correspondingLearningGroup) {
        this.correspondingLearningGroup = correspondingLearningGroup;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public String getAnswerA() {
        return answerA;
    }

    public void setAnswerA(String answerA) {
        this.answerA = answerA;
    }

    public String getAnswerB() {
        return answerB;
    }

    public void setAnswerB(String answerB) {
        this.answerB = answerB;
    }

    public String getAnswerC() {
        return answerC;
    }

    public void setAnswerC(String answerC) {
        this.answerC = answerC;
    }

    public String getAnswerD() {
        return answerD;
    }

    public void setAnswerD(String answerD) {
        this.answerD = answerD;
    }
}
