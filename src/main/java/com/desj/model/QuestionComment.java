package com.desj.model;

import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 * Created by Desi on 6/26/2016.
 */
@Entity
@Component
public class QuestionComment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Lob
    private String comment;

    @OneToOne
    @JoinColumn(name = "QUESTION_ID")
    private Question correspondingQuestion;

    @OneToOne
    @JoinColumn(name = "CREATOR_ID")
    private User creator;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Question getCorrespondingQuestion() {
        return correspondingQuestion;
    }

    public void setCorrespondingQuestion(Question correspondingQuestion) {
        this.correspondingQuestion = correspondingQuestion;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Integer getId() {
        return id;
    }
}
