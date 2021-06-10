package com.goit.messages;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class TextMessage implements Message {


    @Override
    public SendMessage message(Long chatId, String message) {
        SendMessage sendMessageRequest = new SendMessage();
        sendMessageRequest.setChatId(chatId.toString());
        sendMessageRequest.setText(message);
        return sendMessageRequest;
    }
}
