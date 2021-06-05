package com.goit.telegrambot;

import org.apache.commons.validator.routines.EmailValidator;
import org.telegram.telegrambots.meta.api.objects.Update;

public class UserService {

    public static boolean emailCheck(String email){
        return (EmailValidator.getInstance().isValid(email));
    }

    public static User newUser(long chatId){
        String email;
        TelegramApiController registerBot = new TelegramApiController();
        do{
            registerBot.sendText(chatId, "Введи адрес электронной почты: ");

            email = registerBot.getLastMessage();
        }while (!emailCheck(email));

        return new User(chatId, email, "235");
    }

}
