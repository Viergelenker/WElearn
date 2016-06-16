package com.desj.model;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Desi on 6/16/2016.
 */
@Entity
@Transactional
public class Test {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "CREATOR_ID")
    private User creator;

    @JoinTable(name = "TESTQUESTIONS",
            joinColumns = @JoinColumn(name = "TEST_ID"),
            inverseJoinColumns = @JoinColumn(name = "QUESTION_ID"))
    private List<Question> quetionList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "LEARNINGGROUP_ID")
    private LearningGroup associatedLearningGroup;

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

    public List<Question> getQuetionList() {
        return quetionList;
    }

    public void setQuetionList(List<Question> quetionList) {
        this.quetionList = quetionList;
    }

    public LearningGroup getAssociatedLearningGroup() {
        return associatedLearningGroup;
    }

    public void setAssociatedLearningGroup(LearningGroup associatedLearningGroup) {
        this.associatedLearningGroup = associatedLearningGroup;
    }
}
