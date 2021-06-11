package com.goit.user;

import com.goit.api.TelegramApiController;
import com.goit.buttons.SendButton;
import com.goit.buttons.SendText;

import java.util.*;

public class UserInactivityTimer {
    private static final Map<Long, Timer> timerMap = new HashMap<>();
    private static final TelegramApiController service = new TelegramApiController();
    private static final SendText sendText = new SendText();
    private static final SendButton sendButton = new SendButton();

    private UserInactivityTimer(){}


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
                sendButton.sendButton(chatId,"Продолжаем?", listButtons);
            }
        }, 1200000, 1200000);//нужно поменять на время в задании 20 мин.
    }
    //реакция на кнопку таймера неактивности "НЕТ"
    public static void stopUserCheckInactivity(Long chatId) {
        timerMap.get(chatId).cancel();
        sendText.sendText(chatId, "Жаль!\nКогда будешь готов жми кнопку /<b>ДАЛЕЕ</b> вверху!");
    }

    //реакция на кнопку таймера неактивности "ДА"
    public static void continueUserCheckInactivity(Long chatId) {
        sendText.sendText(chatId, "Супер!\nТогда продолжаем, жми кнопку <b>ДАЛЕЕ</b> вверху!");
        updateUserCheckInactivity(chatId);
    }
}