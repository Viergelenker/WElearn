package com.desj.model;

import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Julien on 09.06.16.
 */
@Entity
@Component
public class GroupPost {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    //private String title;
    private String text;

    public Integer getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

   /* public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }*/
}
