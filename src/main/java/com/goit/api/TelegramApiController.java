package com.goit.api;

import com.goit.buttons.SendButton;
import com.goit.telegrambot.UserService;
import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import com.goit.telegrambot.AppProperties;
import java.util.*;

public class TelegramApiController extends TelegramLongPollingBot {

    @SneakyThrows
    @Override
    public String getBotUsername() {return AppProperties.getProperties().getProperty("telegram_bot_name");}

    @SneakyThrows
    @Override
    public String getBotToken() {return AppProperties.getProperties().getProperty("telegram_bot_token");}

    @Override
    public void onUpdateReceived(Update update) {
        new UserService(update).analiseMessage();
    }

    /**
     * send a messages to the telegram user
     * @param sendMessageRequest, SendMessage -  new message
     */
    @SneakyThrows
    public void sendMessage(SendMessage sendMessageRequest) {
        sendApiMethod(sendMessageRequest);
    }

    /**
     * create buttons inline  for the telegram user
     * @param chatId Long, ID current chat in telegram
     * @param text String, message, which wrote to the telegram user
     * @param buttons List of String with button's names
     */
    @SneakyThrows
    public void sendButton(Long chatId, String text, List<String> buttons){
        new SendButton().sendButton(chatId, text, buttons);
    }


}
