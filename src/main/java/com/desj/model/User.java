package com.desj.model;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Julien on 14.04.16.
 */

@Entity
@Component
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String email;
    @Size(min=3, max=20)
    private String username;
    private String password;
    //Studiengang
    private String major;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="LEARNINGGROUPSOFUSER",
            joinColumns=@JoinColumn(name="USER_ID"),
            inverseJoinColumns=@JoinColumn(name="GROUP_ID"))
    List<LearningGroup> learningGroupsOfUser = new ArrayList<>();


    public Integer getId() { return id; }

    public String getEmail() { return email; }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public List<LearningGroup> getLearningGroupsOfUser() {
        return learningGroupsOfUser;
    }

    public void setLearningGroupsOfUser(List<LearningGroup> learningGroupsOfUser) {
        this.learningGroupsOfUser = learningGroupsOfUser;
    }
}
