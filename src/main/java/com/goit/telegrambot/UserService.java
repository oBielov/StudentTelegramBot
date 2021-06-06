package com.goit.telegrambot;

import com.google.api.services.sheets.v4.SheetsRequestInitializer;
import org.telegram.telegrambots.meta.api.objects.Update;

public class UserService {
    private Update update;

    public UserService(Update update) {
        this.update = update;
    }

    public void analiseMessage() {
        if (update.hasMessage() && update.getMessage().hasText()) { handleMessageUpdate(update); }
        if (update.hasCallbackQuery()) { handleCallbackQueryUpdate(update); }
    }

    // Получить и обработать нажатие юзером КНОПКИ
    private void handleCallbackQueryUpdate(Update update){
        Long chatId = update.getCallbackQuery().getFrom().getId();
        String callbackQuery = update.getCallbackQuery().getData();
        if ("answer_yes".equals(callbackQuery)){
            new TelegramApiController().sendText(chatId,"It's all right ;) ");
        }
        else if ("answer_no".equals(callbackQuery)){
            new TelegramApiController().sendText(chatId,"Go to learn!");
        }
    }

    // Получить и обработать текстовое сообщение юзера
    private  void handleMessageUpdate(Update update) {
        Long chatId = update.getMessage().getChatId();
        String messageText = update.getMessage().getText();
        if ("/start".equals(messageText)){
            // тут ищем юзера по chatId
            // ToDo
            Boolean UserIsFound = false;
            // если chatId найден, то без регистрации
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

        else if ("/buttons".equals(messageText)){ // ЭТО ПРИМЕР ВЫВОДА КНОПОК
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
