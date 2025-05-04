package com.example.Millionaire.service;

import com.example.Millionaire.entity.Question;

import java.util.List;

public interface GameService {
    List<Question> getQuestionsGame();
    boolean isPositiveAnswer(int answerId);
}
