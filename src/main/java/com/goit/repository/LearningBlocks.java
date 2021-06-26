package com.goit.repository;

import com.goit.controllers.GoogleApiController;
import com.goit.models.Question;
import com.goit.services.AppProperties;
import com.goit.services.FillingLearningBlock;
import com.goit.services.IFillingLearningBlock;
import com.google.api.services.sheets.v4.model.Sheet;
import com.google.api.services.sheets.v4.model.SheetProperties;
import com.google.api.services.sheets.v4.model.Spreadsheet;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

public class LearningBlocks implements ILearningBlocks {

    private static Map<String, List<Question>> learningBlocks;
    //private IFillingLearningBlock learningBlock;

    static{
        IFillingLearningBlock learningBlock = new FillingLearningBlock();
        List<String> blocks = getBlocks();
        for (String block:blocks) {
            learningBlocks = getBlocks()
                    .stream()
                    .collect(Collectors.toMap(e -> e, e ->learningBlock.fillQuestions(e)))
            ;
        }
    }

    @SneakyThrows
    private static List<String> getBlocks() {
        Properties properties = AppProperties.getProperties();
        String spreadSheetID = properties.getProperty("spreadsheet_id");
        Spreadsheet spreadsheetMetadata = GoogleApiController.service().spreadsheets().get(spreadSheetID).execute();
        List<Sheet> sheets = spreadsheetMetadata.getSheets();
        List<String> blocks = new ArrayList<>();
        sheets.forEach(sheet -> blocks.add(((SheetProperties) sheet.get("properties")).get("title").toString()));
        return blocks;
    }

    public static List<Question> getLearningBlock(String nameBlock){
        return learningBlocks.get(nameBlock);
    }
}
