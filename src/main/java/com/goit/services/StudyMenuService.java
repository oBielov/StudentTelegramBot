package com.goit.services;

import com.goit.models.LearningBlock;
import com.goit.repository.ILearningBlocks;

import java.util.List;
import java.util.stream.Collectors;

public abstract class StudyMenuService {
    //private final LearningBlock learningBlock;

    public StudyMenuService() {
        //this.learningBlock =  ILearningBlocks.of().;
    }

//    protected MenuBlock[] getStudyMenu() {
//        final List<String> blockNames = studyRepository.findAll().stream()
//                .map(StudyBlock::getId)
//                .collect(Collectors.toList());
//        MenuBlock[] studyMenu = new MenuBlock[blockNames.size() + 1];
//        for (int i = 0; i < blockNames.size(); i++) {
//            studyMenu[i] = new StudyButton(blockNames.get(i), "/study_" + blockNames.get(i));
//        }
//        studyMenu[blockNames.size()] = KeyboardButtons.SETTINGS;
//        return studyMenu;
//    }
}
