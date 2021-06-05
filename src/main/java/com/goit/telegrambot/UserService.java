package com.goit.telegrambot;

import org.apache.commons.validator.routines.EmailValidator;
import org.telegram.telegrambots.meta.api.objects.Update;

public class UserService {

    public static boolean emailCheck(String email){
        return (EmailValidator.getInstance().isValid(email));
    }

    public static User newUser(long chatId){
        String email;
        do{
            new TelegramApiController().sendText(chatId, "Введи адрес электронной почты: ");

            email = new TelegramApiController().getLastMessage();
        }while (!emailCheck(email));

        return null;
    }

}
