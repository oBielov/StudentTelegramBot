package com.goit.telegrambot;

import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

public class TelegramApiController extends TelegramLongPollingBot {
    private static Properties appProperties;
    private static final String PROPERTIES_FILEPATH = "/application.properties";

    @SneakyThrows
    @Override
    public String getBotUsername() {return getProperties().getProperty("telegram_bot_name");}

    @SneakyThrows
    @Override
    public String getBotToken() {return getProperties().getProperty("telegram_bot_token");}

    @Override
    public void onUpdateReceived(Update update) {
        new UserService(update).analiseMessage();
    }

    // text to user
    @SneakyThrows
    public void sendText(Long chatId, String text){
        SendMessage sendMessageRequest = new SendMessage();
        sendMessageRequest.setChatId(chatId.toString());
        sendMessageRequest.setText(text);
        sendApiMethod(sendMessageRequest);
    }

    // buttons inline
    @SneakyThrows
    public void sendButton(Long chatId, String text, String[] buttons){
        SendMessage sendMessageRequest = new SendMessage();
        sendMessageRequest.setChatId(chatId.toString());
        sendMessageRequest.setText(text);
        sendMessageRequest.setReplyMarkup(createKeyboard(buttons));
        sendApiMethod(sendMessageRequest);
    }
    // buttons for menu
    @SneakyThrows
    public void sendMenuButton(Long chatId, String text, String[][] buttons){
        SendMessage sendMessageRequest = new SendMessage();
        sendMessageRequest.setChatId(chatId.toString());
        sendMessageRequest.setText(text);
        sendMessageRequest.setReplyMarkup(createMenuKeyboard(buttons));
        sendApiMethod(sendMessageRequest);
    }

    // create buttons
    private ReplyKeyboard createKeyboard(String[] buttons){
        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> listButtons = Arrays.stream(buttons)
                .map(p -> InlineKeyboardButton.builder().text(p).callbackData(p).build())
                .collect(Collectors.toList());
        keyboard.setKeyboard(Collections.singletonList(listButtons));
        return keyboard;
    }

    // create menu buttons
    private ReplyKeyboard createMenuKeyboard(String[][] buttons){
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        List<KeyboardRow> keyboard = new ArrayList<>();
        for (int i = 0; i < buttons.length; i++){
            List<String> list = Arrays.stream(buttons[i]).collect(Collectors.toList());
            KeyboardRow keyboardRow = new KeyboardRow();
            keyboardRow.addAll(list);
            keyboard.add(keyboardRow);
        }
        replyKeyboardMarkup.setKeyboard(keyboard);
        return replyKeyboardMarkup;
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
