package com.goit.controllers;

import com.goit.buttons.MyButton;
import com.goit.handler.TelegramCommandHandler;
import com.goit.services.Application;
import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import com.goit.services.AppProperties;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TelegramApiController extends TelegramLongPollingBot implements MessageSender {

    private Application application = new Application();

    //private final UserRegistrationService userRegistration;
    private final TelegramCommandHandler handler;

    public TelegramApiController() {
        this.handler = TelegramCommandHandler.of();;
    }

    @SneakyThrows
    @Override
    public String getBotUsername() {return AppProperties.getProperties().getProperty("telegram_bot_name");}

    @SneakyThrows
    @Override
    public String getBotToken() {return AppProperties.getProperties().getProperty("telegram_bot_token");}

    @Override
    public void onUpdateReceived(Update update) {
        //application.analiseMessage(update);
        handler.handle(update.getCallbackQuery().getFrom().getId(),
                update.getCallbackQuery().getData(),
                this);
    }

    /**
     * send a messages to the telegram user
     * @param sendMessageRequest, SendMessage -  new message
     */
    @SneakyThrows
    public void sendMessage(SendMessage sendMessageRequest) {
        sendApiMethod(sendMessageRequest);
    }

    @SneakyThrows
    @Override
    public void sendNewMessage(Long chatId, String message) {
        SendMessage sendMessageRequest = new SendMessage();
        sendMessageRequest.setChatId(chatId.toString());
        sendMessageRequest.enableHtml(true);
        sendMessageRequest.setText(message);
        sendApiMethod(sendMessageRequest);
    }

    @SneakyThrows
    @Override
    public void sendNewMessage(Long chatId, String message, List buttons) {
        SendMessage sendMessageRequest = new SendMessage();
        sendMessageRequest.setChatId(chatId.toString());
        sendMessageRequest.enableHtml(true);
        sendMessageRequest.setText(message);
        sendMessageRequest.setReplyMarkup(createKeyboard(buttons));
        sendApiMethod(sendMessageRequest);
    }

    /**
     * create buttons for method sendMenuButton
     * @return ReplyKeyboard - collection of buttons
     * @param buttons array of array of buttons
     * */
    /**
     * create buttons (under the text-box) for method sendButton
     * @return ReplyKeyboard - collection of buttons
     * @param buttons List of String with button's names
     */
    private ReplyKeyboard createKeyboard(List <MyButton> buttons){
        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> listButtons = buttons
                .stream()
                .map(p -> InlineKeyboardButton
                        .builder()
                        .text(p.getTextButton())
                        .callbackData(p.getDataButton())
                        .build())
                .collect(Collectors.toList());
        keyboard.setKeyboard(Collections.singletonList(listButtons));
        return keyboard;
    }

}
