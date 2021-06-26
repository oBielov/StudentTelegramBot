package com.goit.controllers;

import java.util.List;

public interface MessageSender <V>{

    void sendNewMessage(Long chatId, String message, List<V> buttons);

    void sendNewMessage(Long chatId, String message);

}
