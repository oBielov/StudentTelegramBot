package com.goit.messages;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface Message {

    public SendMessage message(Long chatId, String message);


}
