package com.desj.model;

import org.springframework.stereotype.Component;

import javax.persistence.*;
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

    @ManyToMany
    @JoinTable(
            name="GROUPMEMBER",
            joinColumns=@JoinColumn(name="GROUP_ID"),
            inverseJoinColumns=@JoinColumn(name="USER_ID"))
    private List<User> members = new ArrayList<>();


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

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }

}
