package com.goit.services;

import com.goit.controllers.GoogleApiController;
import com.goit.models.LearningBlock;
import com.goit.models.Question;
import com.google.api.services.sheets.v4.model.ValueRange;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;

/**
 * FillingLearningBlock class. Stores information of current learning state, group name and list of Question objects
 * {@link #fillQuestions(String)} - fills List of Question objects with data from Google Spreadsheet
 */
public class FillingLearningBlock implements IFillingLearningBlock <String,Question>{

    private LearningBlock learningBlock;
    private static String sheetId = getSheetId();

    /**
     * Read all rows from spreadsheet fill Question objects with data.
     */
    @Override
    public List<Question> fillQuestions(String course){
        List<List<Object>> values = receiveQuestions(course, sheetId);
        List<Question> questionList = new ArrayList<>();
        for (List row : values){
            if(row.isEmpty()){
                continue;
            }
            questionList.add(new Question(row.get(0).toString(), row.get(1).toString(), row.get(2).toString()));
        }
        List<Question> questions = questionList;
        learningBlock = new LearningBlock(course, questionList);
        return questions;

    }

    /**
     * Connect to spreadsheet and get all cells from given range
     * @return List of List of Objects
     */
    @SneakyThrows
    private List<List<Object>> receiveQuestions(String course, String sheetId){
        String cells = AppProperties.getProperties().getProperty("cells_range");
        ValueRange response = GoogleApiController
                .service()
                .spreadsheets()
                .values()
                .get(sheetId, course + cells)
                .execute();
        return response.getValues();
    }

    @SneakyThrows
    private static String getSheetId(){
        return AppProperties.getProperties().getProperty("spreadsheet_id");//return spreadsheet id
    }

}
