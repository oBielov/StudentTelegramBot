package com.goit.messages;

import com.goit.api.TelegramApiController;
import lombok.SneakyThrows;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SendButton {
    private static final TelegramApiController telegramApiController = new TelegramApiController();
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
        telegramApiController.sendMessage(sendMessageRequest);
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
}
