package com.goit.api;

import com.goit.telegrambot.Application;
import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import com.goit.telegrambot.AppProperties;

public class TelegramApiController extends TelegramLongPollingBot {

    @SneakyThrows
    @Override
    public String getBotUsername() {return AppProperties.getProperties().getProperty("telegram_bot_name");}

    @SneakyThrows
    @Override
    public String getBotToken() {return AppProperties.getProperties().getProperty("telegram_bot_token");}

    @Override
    public void onUpdateReceived(Update update) {
        new Application(update).analiseMessage();
    }

    /**
     * send a messages to the telegram user
     * @param sendMessageRequest, SendMessage -  new message
     */
    @SneakyThrows
    public void sendMessage(SendMessage sendMessageRequest) {
        sendApiMethod(sendMessageRequest);
    }

}
