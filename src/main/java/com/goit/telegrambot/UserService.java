package com.goit.telegrambot;

import com.goit.api.GoogleApiConfig;
import com.goit.messages.Messages;
import com.goit.user.UserList;
import com.google.api.services.sheets.v4.model.Sheet;
import com.google.api.services.sheets.v4.model.SheetProperties;
import com.google.api.services.sheets.v4.model.Spreadsheet;
import lombok.SneakyThrows;
import org.apache.commons.validator.routines.EmailValidator;
import org.telegram.telegrambots.meta.api.objects.Update;
import com.goit.api.TelegramApiController;
import com.goit.user.UserInactivityTimer;

import java.util.*;

public class UserService {
    private Update update;
    private String eMail;
    private String groupNumber;
    private static final TelegramApiController telegramApiController = new TelegramApiController();

    public UserService(Update update) {
        this.update = update;
    }

    public void analiseMessage() {
        if (update.hasMessage() && update.getMessage().hasText()) { handleMessageUpdate(update); }
        if (update.hasCallbackQuery()) { handleCallbackQueryUpdate(update); }
    }

    // Получить и обработать текстовое сообщение юзера
    @SneakyThrows
    private  void handleMessageUpdate(Update update) {
        Long chatId = update.getMessage().getChatId();
        String messageText = update.getMessage().getText();
        UserInactivityTimer.updateUserCheckInactivity(chatId);

        if ("/start".equals(messageText)){
            if (!UserList.isUserExist(chatId)){
                UserList.newUser(chatId); }
        }
        // checking Email & GroupNumber
        eMail = UserList.getEmail(chatId);
        groupNumber = UserList.getGroupNumber(chatId);
        if (eMail.isBlank()){
            if (EmailValidator.getInstance().isValid(messageText)){
                UserList.addEmail(chatId, messageText);
                eMail = UserList.getEmail(chatId);
            }
            else { telegramApiController.sendText(chatId, Messages.askEmail()); }
        }
        if(!eMail.isBlank() && groupNumber.isBlank()) {
            if (groupNumber.isBlank() & !messageText.equals(eMail)) {
                UserList.addGroupNumber(chatId, messageText);
                groupNumber = UserList.getGroupNumber(chatId);
            }
            else telegramApiController.sendText(chatId, Messages.group());
        }
        if (UserList.isUserExist(chatId) && !eMail.isBlank() && !groupNumber.isBlank()
        && UserList.getCurrentQuestion(chatId)==0) {
            List<String> titles = getSections();
            titles.add("Настройки");
            telegramApiController.sendButton(chatId, Messages.welcome(), titles);
        }
    }

    // Получить и обработать нажатие юзером КНОПКИ
    private void handleCallbackQueryUpdate(Update update){
        Long chatId = update.getCallbackQuery().getFrom().getId();
        String callbackQuery = update.getCallbackQuery().getData();
        UserInactivityTimer.updateUserCheckInactivity(chatId);

        List<String> titles = getSections();
        if (titles.contains(callbackQuery)) {
            telegramApiController.sendText(chatId,"выбран раздел обучения '"+callbackQuery+"'");
        }
        if ("Настройки".equals(callbackQuery)) {
            String[][] buttons = new String[][] {
                {"08:00", "09:00", "10:00", "11:00"},
                {"12:00", "13:00", "14:00", "15:00"},
                {"16:00", "17:00", "18:00", "19:00"}
            }; // Выводим под полем ввода меню настройки времени
            telegramApiController.sendMenuButton(chatId,"Выберите в нижнем меню время напоминания", buttons);
        }
    }

    @SneakyThrows
    private List<String> getSections() {
        Properties properties = AppProperties.getProperties();
        String spreadSheetID = properties.getProperty("spreadsheet_id");
        Spreadsheet spreadsheetMetadata = GoogleApiConfig.service().spreadsheets().get(spreadSheetID).execute();
        List<Sheet> sheets = spreadsheetMetadata.getSheets();
        List<String> titles = new ArrayList<>();
        sheets.forEach(sheet -> titles.add(((SheetProperties)sheet.get("properties")).get("title").toString()));
        return titles;
    }
}