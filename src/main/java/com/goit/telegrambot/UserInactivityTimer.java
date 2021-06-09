package com.goit.telegrambot;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class UserInactivityTimer {
    private static Map<Long, Timer> timerMap = new HashMap<>();
    private static final TelegramApiController inactivityTelegramApiController = TelegramApiController.getInstance();

    private UserInactivityTimer(){};


    private static void setUserCheckInactivity(Long chatId) {
        timerMap.put(chatId, new Timer());
        timerMap.get(chatId).schedule(new TimerTask() {
            @Override
            public void run() {
                inactivityTelegramApiController.sendButton(chatId,"Продолжаем?", new String[]{"Да", "Нет"});
            }
        }, 10*60*1000);//нужно поменять на время в задании 20 мин.
    }

    //нужно использовать этот метод при любой активности User и его создании
    public static void updateUserCheckInactivity(Long chatId) {
        if (timerMap.containsKey(chatId)){
            timerMap.get(chatId).cancel();
        }
        setUserCheckInactivity(chatId);
    }
}