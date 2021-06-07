package com.goit.telegrambot;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class UserInactivityTimer {
    private static Map<Long, Timer> timerMap = new HashMap<>();
    private static final TelegramApiController inactivityTelegramApiController = new TelegramApiController();

    //нужно использовать этот метод при активации нового User
    public static void setUserCheckInactivity(Long chatId) {
        timerMap.put(chatId, new Timer());
        timerMap.get(chatId).schedule(new TimerTask() {
            @Override
            public void run() {
                inactivityTelegramApiController.sendButton(chatId,"Продолжаем?", new String[]{"Да", "Нет"});
            }
        }, 20*60*1000);
    }

    //нужно использовать этот метод при любой активности User
    public static void updateUserCheckInactivity(Long chatId) {
        timerMap.get(chatId).cancel();
        setUserCheckInactivity(chatId);
    }
}
