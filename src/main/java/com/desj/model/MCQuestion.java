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
public class MCQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Lob
    private String question;


    private List<String> possibleAnswers = new ArrayList<>();
    @Lob
    private String correctAnswer;


    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name ="GROUP_ID")
    private LearningGroup correspondingLearningGroup;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "CREATOR_ID")
    private User creator;



    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getPossibleAnswers() {
        return possibleAnswers;
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

    public void setPossibleAnswers(List<String> possibleAnswers) {
        this.possibleAnswers = possibleAnswers;
    }


    public void addPossibleAnswer(String s){
        possibleAnswers.add(s);
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
}
