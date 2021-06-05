package com.goit.telegrambot;

import com.google.api.services.sheets.v4.Sheets;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.BotSession;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class Main {

    public static void main(String[] args) throws TelegramApiException, GeneralSecurityException, IOException {
        UserNotificationTimer.run(20, 33);
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(new TelegramApiController());
        Sheets googleService = GoogleApiConfig.service();
    }

}
