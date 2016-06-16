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

    @Size(min = 1, max = 20)
    private String name;

    private String subject;
    private boolean isPrivate;
    private String password;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "USER_ID")
    private User creatorOfGroup;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name="GROUPMEMBER",
            joinColumns=@JoinColumn(name="GROUP_ID"),
            inverseJoinColumns=@JoinColumn(name="USER_ID"))
    private List<User> members = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="MCTests",
            joinColumns=@JoinColumn(name="GROUP_ID"),
           inverseJoinColumns  = @JoinColumn(name = "MCTEST_ID"))
    private List<MCTest> mcTests = new ArrayList<>();


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

}
