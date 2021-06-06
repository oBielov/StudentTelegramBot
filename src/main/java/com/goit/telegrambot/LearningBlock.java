package com.goit.telegrambot;

import com.google.api.services.sheets.v4.Sheets;
import lombok.Data;
import lombok.SneakyThrows;

import java.util.List;

@Data
public class LearningBlock {

    private String groupId;
    private int question;
    List<Question> questions;
    private static Sheets googleService = getService();
    private static String sheetId = getSheetId();

    @SneakyThrows
    private static Sheets getService(){
        return GoogleApiConfig.service();
    }

    @SneakyThrows
    private static String getSheetId(){
        return GoogleApiConfig.getProperties().getProperty("spreadsheet_id");
    }

    public void receiveQuestions(String sheetTitle){

    }

}
