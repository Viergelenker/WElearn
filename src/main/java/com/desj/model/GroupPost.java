package com.desj.model;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    @Lob
    private String text;

    private Date tsCreated;

    @OneToOne
    @JoinColumn(name = "userId")
    private User associatedUser;

    @OneToOne
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
        this.tsCreated = new Date();
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

    public String getTsCreatedFormatted(){
        return new SimpleDateFormat("dd.MM.yyyy HH:mm").format(tsCreated);
    }
}
