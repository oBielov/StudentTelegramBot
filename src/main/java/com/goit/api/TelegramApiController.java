package com.goit.api;

import com.goit.messages.TextMessage;
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
import com.goit.telegrambot.AppProperties;
import java.util.*;
import java.util.stream.Collectors;

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
     * send a text to the telegram user
     * @param chatId Long, ID current chat in telegram
     * @param text String, message, which wrote to the telegram user
     */
    @SneakyThrows
    public void sendText(Long chatId, String text){
        new SendText().sendText(chatId, text);
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

    /**
     * create menu with buttons (under the text-box) for the telegram user
     * @param chatId Long, ID current chat in telegram
     * @param text String, message, which wrote to the telegram user
     * @param buttons array of array of buttons
     */
    @SneakyThrows
    public void sendMenuButton(Long chatId, String text, String[][] buttons){
        new SendMenuButton().sendMenuButton(chatId, text, buttons);
    }


}
