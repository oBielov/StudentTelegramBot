package com.goit.buttons;

import com.goit.api.TelegramApiController;

public class ContinueButton implements Button{

    private final TelegramApiController tService = TelegramApiController.getInstance();
    private final String body = "Далее";
    @Override
    public void sendText() {

    }

    @Override
    public void sendQuery(){

    }
}
