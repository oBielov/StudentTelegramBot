package com.goit.handler;

import com.goit.controllers.MessageSender;

public class HandlerNotifyOff extends TelegramCommandHandler{
    public HandlerNotifyOff(TelegramCommandHandler handler) {
        super(handler);
    }

    @Override
    protected void aplay(Long chatId, String callbackQuery, MessageSender message) {
        userNotificationTimer.checkMenuButtonClick(chatId, callbackQuery);
    }

    @Override
    protected boolean isApplicable(String callbackQuery) {
        return "/notifyOff".equals(callbackQuery);
    }
}
