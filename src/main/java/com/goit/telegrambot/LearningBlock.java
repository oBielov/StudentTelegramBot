package com.goit.telegrambot;

import com.google.api.services.sheets.v4.model.ValueRange;
import lombok.Data;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;

@Data
public class LearningBlock {

    private String groupId;
    private int currentQuestion;
    private List<Question> questions = new ArrayList<>();
    private static String sheetId = getSheetId();


    @SneakyThrows
    private static String getSheetId(){
        return GoogleApiConfig.getProperties().getProperty("spreadsheet_id");
    }

    @SneakyThrows
    private List<List<Object>> receiveQuestions(){
        ValueRange response = GoogleApiConfig.service().spreadsheets().values()
                .get(sheetId, "JavaScrip!A4:C88")
                .execute();
        return response.getValues();
    }

    public void fillQuestions(){
        List<List<Object>> values = receiveQuestions();
        for (List row : values){
            if(row.isEmpty()){
                continue;
            }
            Question question = new Question();
            question.setQuestion(row.get(0).toString());
            question.setAnswer(row.get(1).toString());
            question.setUrl(row.get(2).toString());
            questions.add(question);
        }
    }
    public Question getQuestion(int questionNum){
        return questions.get(questionNum);
    }
}
