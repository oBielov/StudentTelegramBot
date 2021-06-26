package com.goit.handler;

import com.goit.controllers.MessageSender;
import com.goit.repository.ILearningBlocks;
import com.goit.repository.LearningBlocks;
import com.goit.services.UserNotificationTimer;
import com.goit.services.StudyMenuService;

public abstract class TelegramCommandHandler extends StudyMenuService {
    protected final UserNotificationTimer userNotificationTimer;
    protected final LearningBlocks learningBlocks;
    private final TelegramCommandHandler telegramCommandHandler;

    public TelegramCommandHandler(TelegramCommandHandler handler) {
        this.userNotificationTimer = UserNotificationTimer.of();
        this.learningBlocks = ILearningBlocks.of();
        this.telegramCommandHandler = handler;
    }

    protected abstract void aplay(Long chatID, String callbackQuery, MessageSender message);

    protected abstract boolean isApplicable(String callbackQuery);

    public final void handle (Long chatID, String callbackQuery, MessageSender controller){
        if (isApplicable(callbackQuery)){aplay(chatID, callbackQuery, controller);}
        else handle(chatID, callbackQuery, controller);
    }

    public static TelegramCommandHandler of(){
        return new HandlerYes(new HandlerNo(new HandlerSettings(new HandlerStudy(new HandlerNext(
                new HandlerNotify(new HandlerNotifyOff(new HandlerException())))))));
    }
}
