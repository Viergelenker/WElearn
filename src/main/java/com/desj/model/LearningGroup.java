package com.desj.model;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Julien on 23.05.16.
 */
@Entity
@Component
public class LearningGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private String subject;
    private boolean isPrivate;
    private String password;

    @Size(max = 200)
    private String description;

    @OneToOne
    @JoinColumn(name = "USER_ID")
    private User creatorOfGroup;

    @ManyToMany
    @JoinTable(
            name = "GROUPMEMBER",
            joinColumns = @JoinColumn(name = "GROUP_ID"),
            inverseJoinColumns = @JoinColumn(name = "USER_ID"))
    private List<User> members = new ArrayList<>();

    @ElementCollection
    private List<String> uploadedFilesList = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getCreatorOfGroup() {
        return creatorOfGroup;
    }

    public void setCreatorOfGroup(User creatorOfGroup) {
        this.creatorOfGroup = creatorOfGroup;
    }

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }

    public List<String> getUploadedFilesList() {
        return uploadedFilesList;
    }

    public void setUploadedFilesList(List<String> uploadedFilesList) {
        this.uploadedFilesList = uploadedFilesList;
    }
}
