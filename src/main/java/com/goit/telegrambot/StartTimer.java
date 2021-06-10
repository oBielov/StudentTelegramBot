package com.goit.telegrambot;

import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Timer;

public class StartTimer {

    private Update update;

    public StartTimer(Update update) {
        this.update = update;
    }

    //запуск таймера
    public void onTimer(Update update){
        UserNotificationTimer.run(checkTime(update), 0);
    }

    // проверка нажатия на кнопку
    public int checkTime(Update update){
        String time= "";
        Long chatId = update.getMessage().getChatId();
        String messageText = update.getMessage().getText();

        switch (messageText) {
            case "08:00":
                time = "08:00";
                break;
            case "09:00":
                time = "09:00";
                break;
            case "10:00":
                time = "10:00";
                break;
            case "11:00":
                time = "11:00";
                break;
            case "12:00":
                time = "12:00";
                break;
            case "13:00":
                time = "13:00";
                break;
            case "14:00":
                time = "14:00";
                break;
            case "15:00":
                time = "15:00";
                break;
            case "16:00":
                time = "16:00";
                break;
            case "17:00":
                time = "17:00";
                break;
            case "18:00":
                time = "18:00";
                break;
            case "19:00":
                time = "19:00";
                break;
        }
        String substring = time.substring(0, 2);
        return Integer.parseInt(substring);

    }

}
