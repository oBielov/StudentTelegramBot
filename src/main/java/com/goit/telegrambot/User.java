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
    private LearningBlock currentLearningBlock;

    public User(long chatId){
        this.chatId = chatId;
        this.email = "";
        this.groupName = "";
        this.currentLearningBlock.setGroupId("");
        this.currentLearningBlock.setCurrentQuestion(0);
    }

    public void setCurrentQuestion (int question){
        this.currentLearningBlock.setCurrentQuestion(question);
    }
}
