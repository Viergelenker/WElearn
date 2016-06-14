package com.desj.model;

import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 * Created by Sabrina on 14.06.2016.
 */
@Entity
@Component
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne
    @JoinColumn(name="USER_ID")
    private User creator;

    @Lob
    private String text;

    @ManyToOne (cascade = CascadeType.PERSIST)
    @JoinColumn(name = "GROUPPOST_ID")
    private GroupPost associatedGroupPost;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public GroupPost getAssociatedGroupPost() {
        return associatedGroupPost;
    }

    public void setAssociatedGroupPost(GroupPost associatedGroupPost) {
        this.associatedGroupPost = associatedGroupPost;
    }
}
