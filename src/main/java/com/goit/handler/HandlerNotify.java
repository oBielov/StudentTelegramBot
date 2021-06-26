package com.goit.handler;

import com.goit.controllers.MessageSender;
import com.goit.services.UserNotificationTimer;

public class HandlerNotify extends TelegramCommandHandler{
    public HandlerNotify(TelegramCommandHandler handler) {
        super(handler);
    }

    @Override
    protected void aplay(Long chatId, String callbackQuery, MessageSender message) {
        userNotificationTimer.checkMenuButtonClick(chatId, callbackQuery);
    }

    @Override
    protected boolean isApplicable(String callbackQuery) {
        return "/notify".equals(callbackQuery);
    }
}
