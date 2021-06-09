package com.goit.telegrambot;

import org.apache.commons.validator.routines.EmailValidator;
import org.telegram.telegrambots.meta.api.objects.Update;


public class UserService {
    private Update update;

    private boolean isEmail = false;
    private String eMail;
    private String groupNumber;

    public UserService(Update update) {
        this.update = update;
    }

    public void analiseMessage() {
        //UserInactivityTimer.updateUserCheckInactivity(update.getMessage().getChatId());
        if (update.hasMessage() && update.getMessage().hasText()) { handleMessageUpdate(update); }
        if (update.hasCallbackQuery()) { handleCallbackQueryUpdate(update); }
    }

    // Получить и обработать текстовое сообщение юзера
    private  void handleMessageUpdate(Update update) {
        Long chatId = update.getMessage().getChatId();
        String messageText = update.getMessage().getText();
        if ("/start".equals(messageText)){
            // тут ищем юзера по chatId, если нет, то создаем новую запись
            if (UserList.isUserExist(chatId)){
                System.out.println("Continue");
            }
            else UserList.newUser(chatId);

        }
        else if ("/buttons".equals(messageText)){ // ЭТО ПРИМЕР ВЫВОДА КНОПОК
            new TelegramApiController().sendButton(chatId, "Are you ready?", new String[]{"yes","no"});
        }
        // проверяем наличие емейла и номера группы
        eMail = UserList.getEmail(chatId); // это заглушка, потом убрать
        groupNumber = UserList.getGroupNumber(chatId); // это заглушка, потом убрать
        if (eMail.isBlank()){
            // проверить валидность мессаджтекст на емейл если тру записываем в юзер
            if (EmailValidator.getInstance().isValid(messageText)){
                //users.get(chatId).setEmail(messageText);
                UserList.addEmail(chatId, messageText);
                isEmail = true;
                new TelegramApiController().sendText(chatId,"Введите номер группы:");
            }
            //если нет тосообщение->
            else {
                new TelegramApiController().sendText(chatId,"Введите электронный адрес:");
            }
        }
        else if(groupNumber.isBlank() & !eMail.isBlank()) {

            if (groupNumber.isBlank() & !messageText.equals(eMail)) {
                UserList.addGroupNumber(chatId, messageText);
                /* Приветствие и 3 конпки HTML/CSS JS React + кнопка "Настройки"*/
                new TelegramApiController().sendButton(chatId, "Welcome aboard!\n" +
                                "Приветствуем тебя студент, этот бот поможет тебе подготовится к техническим собеседованиям по вебразработке/n" +
                                "но, прежде тебе нужно выбрать блок изучения",
                        new String[]{"HTML/CSS","JS","React","Настройки"});
            }
            else new TelegramApiController().sendText(chatId, "Введите номер группы:");

        }
        else if (UserList.isUserExist(chatId)) {
            new TelegramApiController().sendText(chatId, "Welcome aboard");
            new TelegramApiController().sendText(chatId, "Приветствуем тебя студент, этот бот поможет тебе подготовится к техническим собеседованиям по вебразработке");
            /* 3 конпки HTML/CSS JS React + кнопка "Настройки"*/
            new TelegramApiController().sendButton(chatId, "но прежде тебе нужно выбрать блок изучения",
                    new String[]{"HTML/CSS","JS","React","Настройки"});
        }
    }

    // Получить и обработать нажатие юзером КНОПКИ
    private void handleCallbackQueryUpdate(Update update){
        Long chatId = update.getCallbackQuery().getFrom().getId();
        String callbackQuery = update.getCallbackQuery().getData();
        if ("yes".equals(callbackQuery)){
            new TelegramApiController().sendText(chatId,"It's all right ;) ");
        }
        if ("no".equals(callbackQuery)){
            new TelegramApiController().sendText(chatId,"Go to learn!");
        }
        if ("HTML/CSS".equals(callbackQuery)){
            new TelegramApiController().sendText(chatId,"выбран раздел обучения '"+callbackQuery+"'");
        }
        if ("JS".equals(callbackQuery)){
            new TelegramApiController().sendText(chatId,"выбран раздел обучения '"+callbackQuery+"'");
        }
        if ("React".equals(callbackQuery)){
            new TelegramApiController().sendText(chatId,"выбран раздел обучения '"+callbackQuery+"'");
        }
        if ("Настройки".equals(callbackQuery)){
            new TelegramApiController().sendText(chatId,"Выводим меню настройки бота");
        }
    }
}