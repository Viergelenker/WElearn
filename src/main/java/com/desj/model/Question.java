package com.desj.model;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Desi on 6/16/2016.
 */
@Entity
@Component
public class Question {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Lob
    private String question;

    @Lob
    private String answer;

    @OneToOne
    @JoinColumn(name = "GROUP_ID")
    private LearningGroup correspondingLearningGroup;

    @OneToOne
    @JoinColumn(name = "CREATOR_ID")
    private User creator;

    @OneToMany
    @JoinTable(
            name = "COMMENTS",
            joinColumns = @JoinColumn(name = "QUESTION_ID"),
            inverseJoinColumns = @JoinColumn(name = "COMMENT_ID")
    )
    private List<QuestionComment> comments = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
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

    public List<QuestionComment> getComments() {
        return comments;
    }

    public void setComments(List<QuestionComment> comments) {
        this.comments = comments;
    }
}
