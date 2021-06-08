package com.goit.telegrambot;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class User {

    //id диалога
    private Long chatId;

    private String email;
    private String groupName;
    private LearningBlock currentQuestion;

    public User(long chatId){
        this.chatId = chatId;
        this.email = "";
        this.groupName = "";
        this.currentQuestion.setGroupId("");
        this.currentQuestion.setCurrentQuestion(0);
        this.currentQuestion.getQuestion(0);
    }
}
