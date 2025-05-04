package com.example.Millionaire.service;

import com.example.Millionaire.dao.EmployeeDAO;
import com.example.Millionaire.dao.QuestionDAO;
import com.example.Millionaire.entity.Question;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private QuestionDAO questionDAO;

    @Override
    @Transactional
    public List<Question> getQuestionsGame() {
        return questionDAO.getQuestionsList();
    }

    @Override
    @Transactional
    public boolean isPositiveAnswer(int answerId) {
        return questionDAO.isPositiveAnswer(answerId);
    }
}
