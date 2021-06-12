package com.goit.user;

import com.goit.api.TelegramApiController;
import com.goit.buttons.MyButton;
import com.goit.buttons.SendButton;
import com.goit.buttons.SendText;
import com.goit.messages.Messages;

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
                ArrayList<MyButton> listButtons = new ArrayList<>();
                listButtons.add(new MyButton("Да","/yes"));
                listButtons.add(new MyButton("Нет","/no"));
                sendButton.sendButton(chatId,"Продолжаем?", listButtons);
            }
        },20*60*1000, 20*60*1000);//нужно поменять на время в задании 20 мин.
    }
    //реакция на кнопку таймера неактивности "НЕТ"
    public static void stopUserCheckInactivity(Long chatId) {
        timerMap.get(chatId).cancel();
        sendText.sendText(chatId, Messages.stopCheckInactivity());
    }

    //реакция на кнопку таймера неактивности "ДА"
    public static void continueUserCheckInactivity(Long chatId) {
        sendText.sendText(chatId, Messages.continueCheckInactivity());
        updateUserCheckInactivity(chatId);
    }
}