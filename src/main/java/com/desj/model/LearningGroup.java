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

    @Size(min = 1, max = 30)
    private String name;

    private String subject;
    private boolean isPrivate;
    private String password;

    @Size(max = 200)
    private String description;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "USER_ID")
    private User creatorOfGroup;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "GROUPMEMBER",
            joinColumns = @JoinColumn(name = "GROUP_ID"),
            inverseJoinColumns = @JoinColumn(name = "USER_ID"))
    private List<User> members = new ArrayList<>();

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "QUIZES",
            joinColumns = @JoinColumn(name = "GROUP_ID"),
            inverseJoinColumns = @JoinColumn(name = "MCTEST_ID"))
    private List<Quiz> quizes = new ArrayList<>();

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "MCQUESTIONPOOL",
            joinColumns = @JoinColumn(name = "GROUP_ID"),
            inverseJoinColumns = @JoinColumn(name = "MCQUESTION_ID")
    )
    private List<MCQuestion> mcQuestions = new ArrayList<>();

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "QUESTIONPOOL",
            joinColumns = @JoinColumn(name = "GROUP_ID"),
            inverseJoinColumns = @JoinColumn(name = "QUESTION_ID")
    )
    private List<Question> questions = new ArrayList<>();

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


    public List<Quiz> getQuizes() {
        return quizes;
    }

    public void setQuizes(List<Quiz> quizes) {
        this.quizes = quizes;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public List<MCQuestion> getMcQuestions() {
        return mcQuestions;
    }

    public void setMcQuestions(List<MCQuestion> mcQuestions) {
        this.mcQuestions = mcQuestions;
    }

    public List<String> getUploadedFilesList() {
        return uploadedFilesList;
    }

    public void setUploadedFilesList(List<String> uploadedFilesList) {
        this.uploadedFilesList = uploadedFilesList;
    }
}
