package com.goit.handler;

import com.goit.controllers.MessageSender;
import com.goit.repository.UserInactivityTimer;

public class HandlerYes extends TelegramCommandHandler{
    public HandlerYes(TelegramCommandHandler handler) {
        super(handler);
    }

    @Override
    protected void aplay(Long chatId, String callbackQuery, MessageSender message) {
        UserInactivityTimer.continueUserCheckInactivity(chatId);
    }

    @Override
    protected boolean isApplicable(String callbackQuery) {
        return "/yes".equals(callbackQuery);
    }
}
