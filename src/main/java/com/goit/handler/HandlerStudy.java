package com.goit.handler;

import com.goit.controllers.MessageSender;
import com.goit.models.User;
import com.goit.models.LearningBlock;
import com.goit.repository.LearningBlocks;
import com.goit.repository.UserList;


public class HandlerStudy extends TelegramCommandHandler{
    public HandlerStudy(TelegramCommandHandler handler) {
        super(handler);
    }

    @Override
    protected void aplay(Long chatId, String callbackQuery, MessageSender message) {
        message.sendNewMessage(chatId,"выбран раздел обучения '"+callbackQuery+"'");
        User user = UserList.getUser(chatId);
        user.setCurrentQuestion(0);
        user.setLearningBlock(new LearningBlock(callbackQuery,LearningBlocks.getLearningBlock(callbackQuery)));
        LearningBlock currentBlock = user.getLearningBlock();
        currentBlock.setId(callbackQuery); // set course name
        currentBlock.setQuestions(LearningBlocks.getLearningBlock(callbackQuery));
        HandlerNext.of().aplay(chatId, "/next", message);
    }

    @Override
    protected boolean isApplicable(String callbackQuery) {
        return "/study".equals(callbackQuery);
    }
}
