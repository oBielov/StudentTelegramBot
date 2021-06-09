package com.goit.telegrambot;

import com.google.api.services.sheets.v4.model.ValueRange;
import lombok.Data;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;

/**
 * Learning block class. Stores information of current learning state, group name and list of Question objects
 * {@link #fillQuestions()} - fills List of Question objects with data from Google Spreadsheet
 * {@link #getQuestion(int)} - returns Question object by given index from Array List
 */
@Data
public class LearningBlock {

    private String groupId; //name of group (i.e. JavaScript or HTML)
    private List<Question> questions = new ArrayList<>();//list for Question objects
    private static String sheetId = getSheetId();


    @SneakyThrows
    private static String getSheetId(){
        return GoogleApiConfig.getProperties().getProperty("spreadsheet_id");//return spreadsheet id
    }

    /**
     * Connect to spreadsheet and get all cells from given range
     * @return List of List of Objects
     */
    @SneakyThrows
    private List<List<Object>> receiveQuestions(){
        String cells = GoogleApiConfig.getProperties().getProperty("cells_range");
        ValueRange response = GoogleApiConfig.service().spreadsheets().values()
                .get(sheetId, groupId + cells)
                .execute();
        return response.getValues();
    }

    /**
     * Read all rows from spreadsheet fill Question objects with data.
     */
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

    /**
     * Return Question object from ArrayList by given index
     * @param questionNum index of Question
     * @return Question
     */
    public Question getQuestion(int questionNum){
        return questions.get(questionNum);
    }
}
