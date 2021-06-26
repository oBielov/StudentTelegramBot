package com.goit.handler;

import com.goit.controllers.MessageSender;
import com.goit.repository.UserInactivityTimer;

public class HandlerNo extends TelegramCommandHandler{
    public HandlerNo(TelegramCommandHandler handler) {
        super(handler);
    }

    @Override
    protected void aplay(Long chatId, String callbackQuery, MessageSender message) {
        UserInactivityTimer.stopUserCheckInactivity(chatId);
    }

    @Override
    protected boolean isApplicable(String callbackQuery) {
        return "/no".equals(callbackQuery);
    }
}
