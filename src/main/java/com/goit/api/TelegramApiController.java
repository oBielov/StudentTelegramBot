package com.goit.api;

import com.goit.telegrambot.UserService;
import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

public class TelegramApiController extends TelegramLongPollingBot {
    private static Properties appProperties;
    private static final String PROPERTIES_FILEPATH = "/application.properties";
    private static TelegramApiController service;



    private TelegramApiController(){}

    public static TelegramApiController getInstance(){
        if(service == null){
            synchronized (TelegramApiController.class){
                if(service == null) service = new TelegramApiController();
            }
        }
        return service;
    }

    @SneakyThrows
    @Override
    public String getBotUsername() {return getProperties().getProperty("telegram_bot_name");}

    @SneakyThrows
    @Override
    public String getBotToken() {return getProperties().getProperty("telegram_bot_token");}

    @Override
    public void onUpdateReceived(Update update) {
        new UserService(update);
    }

    /**
     * send a text to the telegram user
     * @param chatId Long, ID current chat in telegram
     * @param text String, message, which wrote to the telegram user
     */
    @SneakyThrows
    public void sendMessage(Long chatId, String text){
        SendMessage sendMessageRequest = new SendMessage();
        sendMessageRequest.setChatId(chatId.toString());
        sendMessageRequest.setText(text);
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
        SendMessage sendMessageRequest = new SendMessage();
        sendMessageRequest.setChatId(chatId.toString());
        sendMessageRequest.setText(text);
        sendMessageRequest.setReplyMarkup(createKeyboard(buttons));
        sendApiMethod(sendMessageRequest);
    }

    /**
     * create menu with buttons (under the text-box) for the telegram user
     * @param chatId Long, ID current chat in telegram
     * @param text String, message, which wrote to the telegram user
     * @param buttons array of array of buttons
     */
    @SneakyThrows
    public void sendMenuButton(Long chatId, String text, String[][] buttons){
        SendMessage sendMessageRequest = new SendMessage();
        sendMessageRequest.setChatId(chatId.toString());
        sendMessageRequest.setText(text);
        sendMessageRequest.setReplyMarkup(createMenuKeyboard(buttons));
        sendApiMethod(sendMessageRequest);
    }

    /**
     * create buttons (under the text-box) for method sendButton
     * @return ReplyKeyboard - collection of buttons
     * @param buttons List of String with button's names
     */
    private ReplyKeyboard createKeyboard(List <String> buttons){
        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> listButtons = buttons
                .stream()
                .map(p -> InlineKeyboardButton.builder().text(p).callbackData(p).build())
                .collect(Collectors.toList());
        keyboard.setKeyboard(Collections.singletonList(listButtons));
        return keyboard;
    }

    /**
     * create buttons for method sendMenuButton
     * @return ReplyKeyboard - collection of buttons
     * @param buttons array of array of buttons
     * */
    private ReplyKeyboard createMenuKeyboard(String[][] buttons){
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        List<KeyboardRow> keyboard = new ArrayList<>();
        for (String[] button : buttons) {
            List<String> list = Arrays.stream(button).collect(Collectors.toList());
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
