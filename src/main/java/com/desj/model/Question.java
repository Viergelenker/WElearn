package com.desj.model;

import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 * Created by Desi on 6/16/2016.
 */
@Entity
@Component
public class Question {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Lob
    private String question;
    @Lob
    private String answer;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "TEST_ID")
    private Test correspondingTest;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Test getCorrespondingTest() {
        return correspondingTest;
    }

    public void setCorrespondingTest(Test correspondingTest) {
        this.correspondingTest = correspondingTest;
    }
}
