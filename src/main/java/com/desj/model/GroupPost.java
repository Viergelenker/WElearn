package com.desj.model;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "userId")
    private User associatedUser;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "learningGroupId")
    private LearningGroup associatedLearningGroup;

    @OneToMany
    @JoinTable(
            name="GROUPCOMMENTS",
            joinColumns=@JoinColumn(name="GROUPPOST_ID"),
            inverseJoinColumns=@JoinColumn(name="COMMENT_ID"))
    private List<Comment> comments = new ArrayList<>();

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

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
