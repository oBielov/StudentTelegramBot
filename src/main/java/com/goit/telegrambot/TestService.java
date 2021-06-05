package com.goit.telegrambot;

import com.google.api.services.sheets.v4.SheetsRequestInitializer;

public class TestService {
    private Long chatId;
    private String message;

    public TestService(Long chatId, String message) {
        this.chatId = chatId;
        this.message = message;
    }

    public void analiseMessage() {
        if ("/start".equals(message)){
            // тут ищем юзера по chatId
            // ToDo
            Boolean UserIsFound = false;

            // если chatId найден, то можно без регистрации
            if (UserIsFound){
                // тут проверяем наличие емейла и номера группы
                // ToDo

                new TelegramApiController().sendText(chatId,"Welcome aboard");
                new TelegramApiController().sendText(chatId,"Приветствуем тебя студент, этот бот поможет тебе подготовится к техническим собеседованиям по вебразработке, но прежде тебе нужно выбрать блок изучения");
            }
            // если chatId НЕ найден, то нужна регистрация
            else{
                new TelegramApiController().sendText(chatId,"Введите электронный адрес:");
            }
        }
        else if ("/buttons".equals(message)){
            new TelegramApiController().sendButton(chatId, "Are you ready?", new String[]{"yes","no"});
        }
        else {
            // тут ищем юзера по chatId
            // ToDo
            Boolean UserIsFound = false;

            // если chatId найден, то можно без регистрации
            if (UserIsFound){
                // тут проверяем наличие емейла и номера группы
                // ToDo

                new TelegramApiController().sendText(chatId,"Welcome aboard");
                new TelegramApiController().sendText(chatId,"Приветствуем тебя студент, этот бот поможет тебе подготовится к техническим собеседованиям по вебразработке, но прежде тебе нужно выбрать блок изучения");
            }
            // если chatId НЕ найден, то нужна регистрация
            else{
                new TelegramApiController().sendText(chatId,"Введите электронный адрес:");
            }
        }

    }
}
