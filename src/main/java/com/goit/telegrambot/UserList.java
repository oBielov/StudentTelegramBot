package com.goit.telegrambot;

import java.util.HashMap;
import java.util.Map;

public class UserList {
    private static Map<Long,User> users = new HashMap<>();

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

    public static boolean isUserExist (long chatId){
        return users.containsKey(chatId);
    }
}
