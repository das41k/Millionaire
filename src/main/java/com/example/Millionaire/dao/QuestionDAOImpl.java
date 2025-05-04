package com.example.Millionaire.dao;

import com.example.Millionaire.entity.Answer;
import com.example.Millionaire.entity.Question;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionDAOImpl implements QuestionDAO {

    @Autowired
    EntityManager entityManager;

    @Override
    public List<Question> getQuestionsList() {
        Session session = entityManager.unwrap(Session.class);
        List<Question> questions = session.createQuery("FROM Question ORDER BY RANDOM() limit 10").getResultList();
        return questions;
    }

    @Override
    public boolean isPositiveAnswer(int answerId) {
        Session session = entityManager.unwrap(Session.class);
        boolean isRight = session.get(Answer.class, answerId).isRight();
        return isRight;
    }
}
