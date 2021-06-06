package com.goit.telegrambot;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Objects;
@Data
@RequiredArgsConstructor
public class User {

    //id диалога
    @NonNull
    private Long chatId;
    //если можно будет передать по api
    private String nickname;

    private String email;
    private String groupName;



    /*public User(long chatId, String email, String groupName) {
        this.chatId = chatId;
        this.email = email;
        this.groupName = groupName;
    }*/

    /*public long getChatId() {
        return chatId;
    }

    public String getEmail() {
        return email;
    }

    public String getGroupName() {
        return groupName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return chatId == user.chatId && Objects.equals(email, user.email) && Objects.equals(groupName, user.groupName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chatId, email, groupName);
    }

    @Override
    public String toString() {
        return "User{" +
                "chatId=" + chatId +
                ", email='" + email + '\'' +
                ", groupName='" + groupName + '\'' +
                '}';
    }*/
}
