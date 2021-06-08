package com.goit.telegrambot;

import com.google.api.services.sheets.v4.Sheets;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.IOException;
import java.security.GeneralSecurityException;

@Slf4j
public class Main {

    public static void main(String[] args) throws TelegramApiException{
        UserNotificationTimer.run(20, 0);
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(new TelegramApiController());

        User user = new User(2123213231L);
        user.setLearningBlockId("JavaScrip");
        user.addQuestions();
        Question question = user.getCurrentQuestion();
        System.out.println(question);
    }

}
