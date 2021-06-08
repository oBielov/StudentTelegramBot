package com.goit.telegrambot;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Objects;
@Data
@RequiredArgsConstructor
public class User {

    //id диалога
    //@NonNull
    private Long chatId;
    private String nickname;

    private String email;
    private String groupName;
    private int currentQuestion;
    private LearningBlock currentBlock;

    public User(long chatId){
        this.chatId = chatId;
        this.email = "";
        this.groupName = "";
        this.currentQuestion = 0;
        this.currentBlock = new LearningBlock();
    }

    public User(long chatId, String email, String groupName) {
        this.chatId = chatId;
        this.email = email;
        this.groupName = groupName;
    }

    public void setLearningBlockId(String groupId){
        currentBlock.setGroupId(groupId);
    }

    public void addQuestions(){
        currentBlock.fillQuestions();
    }

    public Question getCurrentQuestion(){
        return currentBlock.getQuestion(currentQuestion);
    }
}
