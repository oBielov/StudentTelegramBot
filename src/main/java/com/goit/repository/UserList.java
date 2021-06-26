package com.goit.repository;

import com.goit.models.User;

import java.util.HashMap;
import java.util.Map;

public class UserList {
    private static Map<Long, User> users = new HashMap<>();
    
    public static Map<Long,User> getUsers() { return users;}

    public static void newUser(long chatId){
        users.put(chatId, new User(chatId));
    }

    public static void addEmail(long chatId, String email){
        users.get(chatId).setEmail(email);
    }

    public static void addGroupNumber(long chatId, String groupNumber){
        users.get(chatId).setGroupName(groupNumber);
    }

    public static String getEmail(long chatId){
        return users.get(chatId).getEmail();
    }

    public static String getGroupNumber(long chatId){
        return users.get(chatId).getGroupName();
    }

    public static int getCurrentQuestion(long chatId){
        return users.get(chatId).getCurrentQuestion();
    }

    public static boolean isUserExist (long chatId){
        return users.containsKey(chatId);
    }

    public static User getUser(long chatId){return users.get(chatId);}

    public static void setCurrentQuestion (long chatId, int currentQuestion){
        users.get(chatId).setCurrentQuestion(currentQuestion);
    }

}


