package com.goit.telegrambot;
import com.goit.user.LearningBlock;
import com.goit.user.Question;


public class Continue {

    public static String sendText(int question_id, LearningBlock block){
        Question current = block.getQuestion(question_id);

        StringBuilder builder = new StringBuilder();
        builder.append("*Вопрос:* \n")
                .append(current.getQuestion())
                .append("\n\n")
                .append("*Ответ:* \n")
                .append(current.getAnswer())
                .append("\n\n")
                .append("*Видео по теме:* \n")
                .append(current.getUrl());
        return builder.toString();

    }

}
