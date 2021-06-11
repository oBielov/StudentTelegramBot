package com.goit.telegrambot;

import com.goit.api.GoogleApiController;
import com.goit.buttons.Buttons;
import com.goit.messages.Continue;
import com.goit.messages.Messages;
import com.goit.buttons.SendButton;
import com.goit.buttons.SendMenuButton;
import com.goit.buttons.SendText;
import com.goit.user.*;
import com.google.api.services.sheets.v4.model.Sheet;
import com.google.api.services.sheets.v4.model.SheetProperties;
import com.google.api.services.sheets.v4.model.Spreadsheet;
import lombok.SneakyThrows;
import org.apache.commons.validator.routines.EmailValidator;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.*;

public class UserService {
    private final Update update;
    private static final SendText sendText = new SendText();
    private static final SendButton sendButton = new SendButton();
    private static final SendMenuButton sendMenuButton = new SendMenuButton();


    public UserService(Update update) {
        this.update = update;
    }

    public void analiseMessage() {
        if (update.hasMessage() && update.getMessage().hasText()) { handleMessageUpdate(update); }
        if (update.hasCallbackQuery()) { handleCallbackQueryUpdate(update); }
    }

    // Получить и обработать текстовое сообщение юзера
    @SneakyThrows
    private void handleMessageUpdate(Update update) {
        Long chatId = update.getMessage().getChatId();
        String messageText = update.getMessage().getText();
        UserInactivityTimer.updateUserCheckInactivity(chatId);

        if ("/start".equals(messageText)){
            if (!UserList.isUserExist(chatId)){
                UserList.newUser(chatId); }
        }
        // checking Email & GroupNumber
        String eMail = UserList.getEmail(chatId);
        String groupNumber = UserList.getGroupNumber(chatId);
        if (eMail.isBlank()){
            if (EmailValidator.getInstance().isValid(messageText)){
                UserList.addEmail(chatId, messageText);
                eMail = UserList.getEmail(chatId);
            }
            else { sendText.sendText(chatId, Messages.askEmail()); }
        }
        if(!eMail.isBlank() && groupNumber.isBlank()) {
            if (groupNumber.isBlank() & !messageText.equals(eMail)) {
                UserList.addGroupNumber(chatId, messageText);
                groupNumber = UserList.getGroupNumber(chatId);
            }
            else sendText.sendText(chatId, Messages.group());
        }
        if (UserList.isUserExist(chatId) && !eMail.isBlank() && !groupNumber.isBlank()
        && UserList.getCurrentQuestion(chatId)==0) {
            List<String> titles = Messages.blocks();
            sendButton.sendButton(chatId, Messages.welcome(), titles);
        }
    }

    // Получить и обработать нажатие юзером КНОПКИ
    private void handleCallbackQueryUpdate(Update update){
        Long chatId = update.getCallbackQuery().getFrom().getId();
        String callbackQuery = update.getCallbackQuery().getData();
        UserInactivityTimer.updateUserCheckInactivity(chatId);

        List<String> titles = Messages.blocks();
        if (titles.contains(callbackQuery)) {
            sendText.sendText(chatId,"выбран раздел обучения '"+callbackQuery+"'");
            User user = UserList.getUser(chatId);
            user.setCurrentQuestion(0);
            int currentQuestion = user.getCurrentQuestion();
            LearningBlock currentBlock = user.getLearningBlock();
            currentBlock.setGroupId(callbackQuery);
            currentBlock.fillQuestions();
            sendButton.sendButton(chatId, Continue.sendText(user.getCurrentQuestion(),
                    currentBlock), Buttons.nextButton());
            user.setCurrentQuestion(currentQuestion + 1);
        }
        if ("Настройки".equals(callbackQuery)) {
            UserNotificationTimer.sendMenuButton(chatId);
        }
        if ("Далее".equals(callbackQuery)){
            User user = UserList.getUser(chatId);
            LearningBlock currentBlock = user.getLearningBlock();
            int currentQuestion = user.getCurrentQuestion();
            if(currentQuestion == currentBlock.getQuestions().size()){
                sendButton.sendButton(chatId, Messages.endOfBlock(), titles);
            }
            sendButton.sendButton(chatId, Continue.sendText(user.getCurrentQuestion(),
                    currentBlock), Buttons.nextButton());
            user.setCurrentQuestion(currentQuestion + 1);
        }
        if ("Да".equals(callbackQuery)){
            UserInactivityTimer.continueUserCheckInactivity(chatId);
        }
        if ("Нет".equals(callbackQuery)){
            UserInactivityTimer.stopUserCheckInactivity(chatId);
        }
    }


}