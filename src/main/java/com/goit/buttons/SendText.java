package com.goit.buttons;

import com.goit.api.TelegramApiController;
import lombok.SneakyThrows;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class SendText {
    private static final TelegramApiController telegramApiController = new TelegramApiController();
    /**
     * send a text to the telegram user
     * @param chatId Long, ID current chat in telegram
     * @param text String, message, which wrote to the telegram user
     */
    @SneakyThrows
    public void sendText(Long chatId, String text){
        SendMessage sendMessageRequest = new SendMessage();
        sendMessageRequest.enableHtml(true);
        sendMessageRequest.setChatId(chatId.toString());
        sendMessageRequest.setText(text);
        telegramApiController.sendMessage(sendMessageRequest);
    }
}
