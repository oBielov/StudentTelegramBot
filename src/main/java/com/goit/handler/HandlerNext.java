package com.goit.handler;

import com.goit.buttons.Buttons;
import com.goit.buttons.MyButton;
import com.goit.controllers.MessageSender;
import com.goit.messages.Continue;
import com.goit.messages.Messages;
import com.goit.models.User;
import com.goit.models.LearningBlock;
import com.goit.repository.UserList;

import java.util.stream.Collectors;

public class HandlerNext extends TelegramCommandHandler{
    public HandlerNext(TelegramCommandHandler handler) {
        super(handler);
    }

    @Override
    protected void aplay(Long chatId, String callbackQuery, MessageSender message) {
        User user = UserList.getUser(chatId);
        LearningBlock currentBlock = user.getLearningBlock();
        int currentQuestion = user.getCurrentQuestion();
        if (currentQuestion == currentBlock.getQuestions().size()) {
            message.sendNewMessage(chatId, Messages.endOfBlock(),
                    Messages.blocks().stream().map(p -> new MyButton(p,p)).collect(Collectors.toList()));
            return;
        }
        message.sendNewMessage(chatId, Continue.sendText(user.getCurrentQuestion(),
                currentBlock), Buttons.nextButton());
        user.setCurrentQuestion(currentQuestion + 1);
    }

    @Override
    protected boolean isApplicable(String callbackQuery) {
        return "/next".equals(callbackQuery);
    }


}
