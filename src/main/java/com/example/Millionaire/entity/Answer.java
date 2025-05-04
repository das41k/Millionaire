package com.example.Millionaire.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "answers")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "text")
    private String text;

    @Column(name = "is_right")
    private boolean isRight;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    public void setId(int id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setRight(boolean right) {
        isRight = right;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public boolean isRight() {
        return isRight;
    }

    public String getText() {
        return text;
    }

    public int getId() {
        return id;
    }

    public Question getQuestion() {
        return question;
    }

}
