package com.goit.telegrambot;

import com.google.api.services.sheets.v4.model.Sheet;
import com.google.api.services.sheets.v4.model.SheetProperties;
import com.google.api.services.sheets.v4.model.Spreadsheet;
import lombok.SneakyThrows;
import org.apache.commons.validator.routines.EmailValidator;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.*;

public class UserService {
    private Update update;
    private String eMail;
    private String groupNumber;
    private String[] sections;

    public UserService(Update update) {
        this.update = update;
    }

    public void analiseMessage() {
        //UserInactivityTimer.updateUserCheckInactivity(update.getMessage().getChatId());
        if (update.hasMessage() && update.getMessage().hasText()) { handleMessageUpdate(update); }
        if (update.hasCallbackQuery()) { handleCallbackQueryUpdate(update); }
    }

    // Получить и обработать текстовое сообщение юзера
    @SneakyThrows
    private  void handleMessageUpdate(Update update) {
        Long chatId = update.getMessage().getChatId();
        String messageText = update.getMessage().getText();
        TelegramApiController telegramApiController = new TelegramApiController();

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
            else { telegramApiController.sendText(chatId,"Введите электронный адрес:"); }
        }
        if(groupNumber.isBlank() & !eMail.isBlank()) {
            if (groupNumber.isBlank() & !messageText.equals(eMail)) {
                UserList.addGroupNumber(chatId, messageText);
                groupNumber = UserList.getGroupNumber(chatId);
            }
            else telegramApiController.sendText(chatId, "Введите номер группы:");
        }
        if (UserList.isUserExist(chatId) && UserList.getCurrentQuestion(chatId)==0
                && !groupNumber.isBlank() && !eMail.isBlank()) {

            List<String> titles = getSections();
            titles.add("Настройки");
            String[] sections = new String[titles.size()];
            sections = titles.toArray(sections);
            telegramApiController.sendButton(chatId,"Приветствуем тебя студент, этот бот поможет тебе подготовиться" +
                            " к техническим собеседованиям по вебразработке\n" +
                            "но, прежде тебе нужно выбрать блок изучения",
                    sections);
        }
//        if (UserList.getCurrentQuestion(chatId)!=0){
//            UserList.setCurrentQuestion(chatId, 1);
//            telegramApiController.sendText(chatId, "Выводим очередной вопрос...");
//        }
    }

    // Получить и обработать нажатие юзером КНОПКИ
    private void handleCallbackQueryUpdate(Update update){
        Long chatId = update.getCallbackQuery().getFrom().getId();
        String callbackQuery = update.getCallbackQuery().getData();
        TelegramApiController telegramApiController = new TelegramApiController();

        List<String> titles = getSections();
        String[] sections = new String[titles.size()];
        sections = titles.toArray(sections);
        if (titles.indexOf(callbackQuery) >=0) {
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
        Properties properties = GoogleApiConfig.getProperties();
        String spreadSheetID = properties.getProperty("spreadsheet_id");
        Spreadsheet spreadsheetMetadata = GoogleApiConfig.service().spreadsheets().get(spreadSheetID).execute();
        List<Sheet> sheets = spreadsheetMetadata.getSheets();
        List<String> titles = new ArrayList<>();
        sheets.forEach(sheet -> titles.add(((SheetProperties)sheet.get("properties")).get("title").toString()));            ArrayList<String> list = new ArrayList<>();
        return titles;
    }
}