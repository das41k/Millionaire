package com.example.Millionaire.controller;

import com.example.Millionaire.entity.Question;
import com.example.Millionaire.service.EmployeeService;
import com.example.Millionaire.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class GameController {

    @Autowired
    private GameService gameService;

    private int currentQuestionIndex = 0;
    private List<Question> questions;

    @RequestMapping("/game")
    public String gameStart(Model model) {
        questions = gameService.getQuestionsGame();
        model.addAttribute("questions", questions);
        model.addAttribute("question", questions.get(currentQuestionIndex));
        model.addAttribute("questionNumber", currentQuestionIndex + 1);
        model.addAttribute("totalQuestions", questions.size());
        return "game";
    }

    @RequestMapping("/game-answer")
    public String processAnswer(@RequestParam int answerId, Model model) {
        if (!gameService.isPositiveAnswer(answerId)) {
            model.addAttribute("showResultModal", true);
            model.addAttribute("isWin", false);
            model.addAttribute("question", questions.get(currentQuestionIndex));
            model.addAttribute("questionNumber", currentQuestionIndex + 1);
            model.addAttribute("totalQuestions", questions.size());
            return "game";
        } else {
            // Правильный ответ - переходим к следующему вопросу
            currentQuestionIndex++;
            if (currentQuestionIndex < questions.size()) {
                model.addAttribute("question", questions.get(currentQuestionIndex));
                model.addAttribute("questionNumber", currentQuestionIndex + 1);
                model.addAttribute("totalQuestions", questions.size());
                return "game";
            } else {
                model.addAttribute("showResultModal", true);
                model.addAttribute("isWin", true);
                return "game";
            }
        }
    }

    @RequestMapping(value = "/game-end")
    public String endGame() {
        currentQuestionIndex = 0; // Сбрасываем игру
        return "room";
    }
}
