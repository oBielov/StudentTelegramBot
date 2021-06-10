package com.goit.user;

import com.goit.api.TelegramApiController;

import java.util.*;

public class UserInactivityTimer {
    private static final Map<Long, Timer> timerMap = new HashMap<>();
    private static final TelegramApiController inactivityTelegramApiController = TelegramApiController.getInstance();

    private UserInactivityTimer(){};


    //нужно использовать этот метод при любой активности User и его создании
    public static void updateUserCheckInactivity(Long chatId) {
        if (timerMap.containsKey(chatId)) {
            timerMap.get(chatId).cancel();
        }
        timerMap.put(chatId, new Timer());
        timerMap.get(chatId).schedule(new TimerTask() {
            @Override
            public void run() {
                ArrayList<String> listButtons = new ArrayList<>();
                listButtons.add("Да");
                listButtons.add("Нет");
                inactivityTelegramApiController.sendButton(chatId,"Продолжаем?", listButtons);
            }
        }, 30 * 1000, 30 * 1000);//нужно поменять на время в задании 20 мин.
    }
}