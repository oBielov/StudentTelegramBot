package com.goit.handler;

import com.goit.controllers.MessageSender;

public class HandlerSettings extends TelegramCommandHandler{
    public HandlerSettings(TelegramCommandHandler handler) {
        super(handler);
    }

    @Override
    protected void aplay(Long chatId, String callbackQuery, MessageSender message) {
        userNotificationTimer.sendMenuButton(chatId);
    }

    @Override
    protected boolean isApplicable(String callbackQuery) {
        return "/settings".equals(callbackQuery);
    }
}
