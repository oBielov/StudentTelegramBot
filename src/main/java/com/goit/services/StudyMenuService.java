package com.goit.services;

import com.goit.repository.LearningBlock;

import java.util.List;

public abstract class StudyMenuService {
    private final LearningBlock learningBlock;

    public StudyMenuService() {
        this.learningBlock = new LearningBlock();
    }

    protected MenuBlock[] getStudyMenu() {
        final List<String> blockNames = learningBlock.stream();

    }
}
