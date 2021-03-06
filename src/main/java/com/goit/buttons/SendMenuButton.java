package com.goit.buttons;

import com.goit.api.TelegramApiController;
import lombok.SneakyThrows;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SendMenuButton {
    private static final TelegramApiController telegramApiController = new TelegramApiController();
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
        sendMessageRequest.enableHtml(true);
        sendMessageRequest.setText(text);
        sendMessageRequest.setReplyMarkup(createMenuKeyboard(buttons));
        telegramApiController.sendMessage(sendMessageRequest);
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
}
