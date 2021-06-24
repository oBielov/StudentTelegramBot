package com.goit.handler;

import com.goit.messages.Message;

public class HandlerStudy extends TelegramCommandHandler{
    @Override
    protected void aplay(Long chatID, String callbackQuery, Message message) {

    }

    @Override
    protected boolean isApplicable(String callbackQuery) {
        return false;
    }
}
