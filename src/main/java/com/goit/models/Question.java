package com.goit.models;

import lombok.Data;

@Data
public class Question implements BaseEntity <Integer>{

    private Integer id;
    private String question;
    private String answer;
    private String url;

    public Question(String question, String answer, String url) {
        this.id = Integer.parseInt(question.substring(0, question.indexOf(".")));
        this.question = question;
        this.answer = answer;
        this.url = url;
    }

}