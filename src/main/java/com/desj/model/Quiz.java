package com.desj.model;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Desi on 6/15/2016.
 */
@Entity
@Component
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;



    private Integer questionQuantity;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "learningGroupId")
    private LearningGroup learningGroup;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "TESTQUESTIONS",
            joinColumns = @JoinColumn(name = "MCTEST_ID"),
            inverseJoinColumns = @JoinColumn(name = "QUESTION_ID"))

    private List<MCQuestion> MCQuestions = new ArrayList<>();


    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }



    public LearningGroup getLearningGroup() {
        return learningGroup;
    }

    public void setLearningGroup(LearningGroup learningGroup) {
        this.learningGroup = learningGroup;
    }

    public List<MCQuestion> getQuestions() {
        return MCQuestions;
    }

    public void setQuestions(List<MCQuestion> MCQuestions) {
        this.MCQuestions = MCQuestions;
    }

    public Integer getQuestionQuantity() {
        return questionQuantity;
    }

    public void setQuestionQuantity(Integer questionQuantity) {
        this.questionQuantity = questionQuantity;
    }

    public List<MCQuestion> getMCQuestions() {
        return MCQuestions;
    }

    public void setMCQuestions(List<MCQuestion> MCQuestions) {
        this.MCQuestions = MCQuestions;
    }
}
