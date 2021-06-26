package com.goit.repository;

public interface ILearningBlocks {

    static LearningBlocks of(){
        return new LearningBlocks();
    }
}
