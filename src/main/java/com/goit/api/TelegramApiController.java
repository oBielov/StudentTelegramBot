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
<<<<<<<<< Temporary merge branch 1:src/main/java/com/goit/telegrambot/TelegramApiController.java
        new SendText().sendText(chatId, text);
=========
        TextMessage textMessage = new TextMessage();
        sendApiMethod(textMessage.message(chatId, text));
>>>>>>>>> Temporary merge branch 2:src/main/java/com/goit/api/TelegramApiController.java
    }

    /**
     * create buttons inline  for the telegram user
     * @param chatId Long, ID current chat in telegram
     * @param text String, message, which wrote to the telegram user
     * @param buttons List of String with button's names
     */
    @SneakyThrows
    public void sendButton(Long chatId, String text, List<String> buttons){
<<<<<<<<< Temporary merge branch 1:src/main/java/com/goit/telegrambot/TelegramApiController.java
        new SendButton().sendButton(chatId, text, buttons);
=========
        SendMessage sendMessageRequest = new SendMessage();
        sendMessageRequest.enableMarkdown(true);
        sendMessageRequest.setChatId(chatId.toString());
        sendMessageRequest.setText(text);
        sendMessageRequest.setReplyMarkup(createKeyboard(buttons));
        sendApiMethod(sendMessageRequest);
>>>>>>>>> Temporary merge branch 2:src/main/java/com/goit/api/TelegramApiController.java
    }

    /**
     * create menu with buttons (under the text-box) for the telegram user
     * @param chatId Long, ID current chat in telegram
     * @param text String, message, which wrote to the telegram user
     * @param buttons array of array of buttons
     */
    @SneakyThrows
    public void sendMenuButton(Long chatId, String text, String[][] buttons){
<<<<<<<<< Temporary merge branch 1:src/main/java/com/goit/telegrambot/TelegramApiController.java
        new SendMenuButton().sendMenuButton(chatId, text, buttons);
=========
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
>>>>>>>>> Temporary merge branch 2:src/main/java/com/goit/api/TelegramApiController.java
    }


}
