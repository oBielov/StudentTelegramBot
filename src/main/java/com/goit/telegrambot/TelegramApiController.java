package com.goit.telegrambot;

import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class TelegramApiController extends TelegramLongPollingBot {
    private static Properties appProperties;
    private static final String PROPERTIES_FILEPATH = "/application.properties";
//    private Long lastChatId;
//    private String lastMessage;

    @SneakyThrows
    @Override
    public String getBotUsername() {return getProperties().getProperty("telegram_bot_name");}

    @SneakyThrows
    @Override
    public String getBotToken() {return getProperties().getProperty("telegram_bot_token");}

    @Override
    public void onUpdateReceived(Update update) {
//        Long chatId = update.getMessage().getChatId();
//        String messageText = update.getMessage().getText();
        new UserService(update).analiseMessage();
    }

    // написать юзеру текст
    public void sendText(Long chatId, String text){
        SendMessage sendMessageRequest = new SendMessage();
        sendMessageRequest.setChatId(chatId.toString());
        sendMessageRequest.setText(text);
        try {
            sendApiMethod(sendMessageRequest);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }//end catch()
    }

    // вывести юзеру кнопки
    public void sendButton(Long chatId, String text, String[] buttons){
        SendMessage sendMessageRequest = new SendMessage();
        sendMessageRequest.setChatId(chatId.toString());
        sendMessageRequest.setText(text);
        sendMessageRequest.setReplyMarkup(createKeyboard(buttons));
        try {
            sendApiMethod(sendMessageRequest);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }//end catch()
    }

    // создать кнопки
    private ReplyKeyboard createKeyboard(String[] buttons){
        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
        // добавить формирование листа с кнопками
        List<InlineKeyboardButton> listButtons = Arrays.stream(buttons)
                .map(p -> InlineKeyboardButton.builder().text(p).callbackData(p).build())
                .collect(Collectors.toList());
        keyboard.setKeyboard(Collections.singletonList(listButtons));
        return keyboard;
    }

    /**
     * Properties class that returns properties from /application.properties. User can access property by
     * getProperties().getProperty() method.
     * @return Properties
     * @throws IOException if there is no application.property file
     * @see Properties
     */
    private static Properties getProperties() throws IOException {
        if (appProperties != null){
            return appProperties;
        }
        InputStream in = TelegramApiController.class.getResourceAsStream(PROPERTIES_FILEPATH);
        if (in == null){
            throw new FileNotFoundException("No such resource: " + PROPERTIES_FILEPATH);
        }
        appProperties = new Properties();
        appProperties.load(in);
        return appProperties;
    }
}

