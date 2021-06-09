package com.goit.buttons;

import com.goit.telegrambot.TelegramApiController;

public class ContinueButton implements Button{

    private final TelegramApiController teleService = TelegramApiController.getInstance();
    private final String body = "Далее";
    @Override
    public void execute() {

    }
}
