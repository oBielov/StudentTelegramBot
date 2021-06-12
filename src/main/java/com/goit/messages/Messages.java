package com.goit.messages;

import com.goit.api.GoogleApiController;
import com.goit.telegrambot.AppProperties;
import com.google.api.services.sheets.v4.model.Sheet;
import com.google.api.services.sheets.v4.model.SheetProperties;
import com.google.api.services.sheets.v4.model.Spreadsheet;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Messages {

    public static String askEmail() {
        return "Пожалуйста, введите корректный e-mail: ";
    }

    public static String welcome() {
        return "Приветствуем тебя студент, этот бот поможет тебе подготовиться" +
                " к техническим собеседованиям по вебразработке.\n" +
                "Но, прежде тебе нужно выбрать блок изучения";
    }

    public static String group() {
        return "Введите номер группы: ";
    }

    public static String endOfBlock() {
        return "<b>Поздравляем! Вы закончили этот блок обучения.\nВы можете начать заново или выбрать другой блок  </b>";
    }


    @SneakyThrows
    public static List<String> blocks() {
        Properties properties = AppProperties.getProperties();
        String spreadSheetID = properties.getProperty("spreadsheet_id");
        Spreadsheet spreadsheetMetadata = GoogleApiController.service().spreadsheets().get(spreadSheetID).execute();
        List<Sheet> sheets = spreadsheetMetadata.getSheets();
        List<String> titles = new ArrayList<>();
        sheets.forEach(sheet -> titles.add(((SheetProperties) sheet.get("properties")).get("title").toString()));
        return titles;
    }

    public static String sendMenuButton() {
        return "Выберите в нижнем меню время напоминания";
    }

    public static String clickMenuButton() {
        return "Напоминание установленно на: ";
    }

    public static String notificationOff() {
        return "Вы отключили напоминание!\nЧтобы возобновить выберите время в меню.";
    }

    public static String stopCheckInactivity() {
        return "Окей, я останавливаю тренировку. Можешь продолжить в любое время когда будешь готов.";
    }
}
