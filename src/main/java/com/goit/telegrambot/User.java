package com.goit.telegrambot;

import java.util.Objects;

public class User {

    //id диалога
    private long chatId;

    private String email;
    private String groupName;
    private LearningBlock learningBlock;

    public User(long chatId) {
        this.chatId = chatId;
    }

    public long getChatId() {
        return chatId;
    }

    public String getEmail() {
        return email;
    }

    public String getGroupName() {
        return groupName;
    }

    public LearningBlock getLearningBlock() {
        return learningBlock;
    }

    public void setLearningBlock(LearningBlock learningBlock) {
        this.learningBlock = learningBlock;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
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
    }
}
