package com.desj.model;

import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 * Created by Julien on 09.06.16.
 */
@Entity
@Component
public class GroupPost {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String title;
    private String text;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "userId")
    private User associatedUser;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "learningGroupId")
    private LearningGroup associatedLearningGroup;

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getAssociatedUser() {
        return associatedUser;
    }

    public void setAssociatedUser(User associatedUser) {
        this.associatedUser = associatedUser;
    }

    public LearningGroup getAssociatedLearningGroup() {
        return associatedLearningGroup;
    }

    public void setAssociatedLearningGroup(LearningGroup associatedLearningGroup) {
        this.associatedLearningGroup = associatedLearningGroup;
    }
}
