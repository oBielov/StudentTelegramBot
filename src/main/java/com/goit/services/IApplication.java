package com.goit.services;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface IApplication {

    public void analiseMessage(Update update) {}
}
