package com.goit.services;

import com.goit.controllers.MessageSender;

public interface IUserRegistration {

    void execute(Long chatId, String text, MessageSender controller);

    static IUserRegistration of() {
        return new UserRegistration();
    }
}
