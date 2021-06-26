package com.goit.models;

import com.goit.controllers.GoogleApiController;
import com.goit.services.AppProperties;
import com.google.api.services.sheets.v4.model.ValueRange;
import lombok.Data;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;

/**
 * Learning block class. Stores information of current learning state, group name and list of Question objects
 * {@link #getQuestion(int)} - returns Question object by given index from Array List
 */
@Data
public class LearningBlock implements BaseEntity<String>{

    private String id; //name of group (i.e. 'JavaScript' or 'HTML')
    private List<Question> questions = new ArrayList<>(); //List of Question objects

    public LearningBlock(String course, List<Question> questions) {
        this.id = course;
        this.questions = questions;
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
