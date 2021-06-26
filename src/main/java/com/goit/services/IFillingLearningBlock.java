package com.goit.services;

import java.util.List;

public interface IFillingLearningBlock <T,V>{

    public List<V> fillQuestions(T course);


}
