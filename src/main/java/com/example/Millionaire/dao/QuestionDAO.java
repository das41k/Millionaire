package com.example.Millionaire.dao;

import com.example.Millionaire.entity.Question;

import java.util.List;

public interface QuestionDAO {
    List<Question> getQuestionsList();
    boolean isPositiveAnswer(int answerId);
}
