package com.goit.telegrambot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TextMessageBot extends TelegramLongPollingBot {
    private static final String PROPERTIES_FILEPATH = "/application.properties"; //properties with ID

    @Override
    public String getBotUsername() {
        //тут получаем BOT_NAME
        String botName = "MyGoITTelegramBot"; //PROPERTIES_FILEPATH.BOT_NAME
        return botName;
    }

    @Override
    public String getBotToken() {
        //тут получаем BOT_TOKEN
        String botToken = "1759009132:AAGfyY-S0r0zFT06Rr3k0oC1jgljQK016tY"; //TelegramConstants.BOT_TOKEN
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()){
            handleMessageUpdate(update);
        }
    }

    private  void handleMessageUpdate(Update update) {
        Long chatId = update.getMessage().getChatId();
        String messageText = update.getMessage().getText();

        sendText(chatId, "Ви написали: " + messageText);
    }
    private void sendText(Long chatId, String text){
        SendMessage sendMessageRequest = new SendMessage();
        sendMessageRequest.setChatId(chatId.toString()); //who should get the message? the sender from which we got the message...
        sendMessageRequest.setText(text);
        try {
            sendApiMethod(sendMessageRequest);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }//end catch()


    }
}

