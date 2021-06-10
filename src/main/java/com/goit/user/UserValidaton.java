package com.goit.user;

import com.goit.api.TelegramApiController;
import org.apache.commons.validator.routines.EmailValidator;

public class UserValidaton {

    private static final TelegramApiController service = new TelegramApiController();

    private static String valid(long chatId, String email){
        if(EmailValidator.getInstance().isValid(email)){
            return "valid";
        }
        else{
            return "invalid";
        }

    }

    private static User registerUser(long chatId, String email){
        UserList.newUser(chatId);
        UserList.addEmail(chatId, email);
        UserList.setCurrentQuestion(chatId, 0);

        return UserList.getUser(chatId);
    }

//    public static User setGroup(User user, int groupNumber){
//
//    }


    public static User getUser(long chatId){
        return UserList.getUser(chatId);
    }

}
