package com.goit.user;

import lombok.Data;
import lombok.RequiredArgsConstructor;

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
    private LearningBlock learningBlock = new LearningBlock();

    public User(long chatId){
        this.chatId = chatId;
        this.email = "";
        this.groupName = "";
        this.currentQuestion = 0;
    }

    public User(long chatId, String email, String groupName) {
        this.chatId = chatId;
        this.email = email;
        this.groupName = groupName;
    }
}
