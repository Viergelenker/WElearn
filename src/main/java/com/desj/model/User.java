package com.desj.model;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    @Size(min = 6, max = 30)
    private String email;
    private String username;
    @NotNull
    private String password;
    //Studiengang
    private String major;

    @ManyToMany
    @JoinTable(
            name = "ANSWEREDMCQUESTIONS",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "MCQUESTION_ID")
    )
    List<MCQuestion> answeredMCQuestions = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

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

    public List<MCQuestion> getAnsweredMCQuestions() {
        return answeredMCQuestions;
    }

    public void setAnsweredMCQuestions(List<MCQuestion> answeredMCQuestions) {
        this.answeredMCQuestions = answeredMCQuestions;
    }
}
