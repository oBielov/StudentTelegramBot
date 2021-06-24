package com.goit.handler;

import com.goit.messages.Message;
import com.goit.repository.LearningBlock;
import com.goit.repository.UserNotificationTimer;
import com.goit.services.StudyMenuService;

public abstract class TelegramCommandHandler extends StudyMenuService {
    protected final UserNotificationTimer userNotificationTimer;
    protected final LearningBlock learningBlock;
    private final TelegramCommandHandler telegramCommandHandler;

    public TelegramCommandHandler(TelegramCommandHandler handler) {
        this.userNotificationTimer = UserNotificationTimer.of();
        this.learningBlock = LearningBlock.of();
        this.telegramCommandHandler = handler;
    }

    protected abstract void aplay(Long chatID, String callbackQuery, Message message);

    protected abstract boolean isApplicable(String callbackQuery);

    public static TelegramCommandHandler of(){
        return new HandlerYes(new HandlerNo(new HandlerNext()));
    }
}
