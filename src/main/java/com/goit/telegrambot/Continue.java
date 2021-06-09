package com.goit.telegrambot;


public class Continue {

    public static String sendText(int question_id, LearningBlock block){
        Question current = block.getQuestion(question_id);

        StringBuilder builder = new StringBuilder();
        builder.append("<b>Вопрос:</b> \n")
                .append(current.getQuestion())
                .append("\n")
                .append("<b>Ответ:</b> \n")
                .append(current.getAnswer())
                .append("\n")
                .append("<b>Видео по теме:</b> \n")
                .append(current.getUrl());
        return builder.toString();

    }

}
