package com.goit.handler;

import com.goit.controllers.MessageSender;

public class HandlerException extends TelegramCommandHandler{

    public HandlerException() {
        super(null);
    }

    @Override
    protected void aplay(Long chatId, String callbackQuery, MessageSender message) {
        throw new RuntimeException("Command '" + callbackQuery + "' not found");
    }

    @Override
    protected boolean isApplicable(String callbackQuery) {
        return true;
    }
}
