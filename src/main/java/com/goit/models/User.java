package com.goit.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class User implements BaseEntity<Long>{

    //id диалога
    //@NonNull
    private Long id; //chatId
    private String nickname;
    private String email;
    private String groupName;
    private String courseName;
    private int currentQuestion;
    private LearningBlock learningBlock;

    public User(long chatId){
        new User( chatId, "", "");
    }

    public User(long chatId, String email, String groupName) {
        this.id = chatId;
        this.email = email;
        this.groupName = groupName;
        this.currentQuestion = 0;
    }

}
