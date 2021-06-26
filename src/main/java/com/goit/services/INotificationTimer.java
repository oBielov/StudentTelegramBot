package com.goit.services;

public interface INotificationTimer {

    void sendMenuButton(Long chatId);

    void checkMenuButtonClick(Long chatId, String messageText);

    void run(Long chatId, int hours, int minutes);

    void notificationOff(Long chatId);

    void setDefaultNotificationTimer(Long chatId);

}
