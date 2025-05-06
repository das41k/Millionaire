package com.example.Millionaire.controller;

import com.example.Millionaire.entity.Answer;
import com.example.Millionaire.entity.Employee;
import com.example.Millionaire.entity.Question;
import com.example.Millionaire.service.EmployeeService;
import com.example.Millionaire.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@SessionAttributes({"employee", "currentQuestionIndex", "questions", "currentAmount", "fiftyFiftyUsed", "skipUsed"})
public class GameController {

    @ModelAttribute("fiftyFiftyUsed")
    public Boolean setUpFiftyFiftyUsed() {
        return false;
    }

    @ModelAttribute("skipUsed")
    public Boolean setUpSkipUsed() {
        return false;
    }

    @Autowired
    private GameService gameService;

    @Autowired
    private EmployeeService employeeService;

    @ModelAttribute("currentQuestionIndex")
    public Integer setUpCurrentQuestionIndex() {
        return 0;
    }

    @ModelAttribute("currentAmount")
    public Integer setUpCurrentAmount() {
        return 100000;
    }

    @RequestMapping("/game")
    public String gameStart(@ModelAttribute("employee") Employee employee,
                            @ModelAttribute("currentQuestionIndex") Integer currentQuestionIndex,
                            @ModelAttribute("currentAmount") Integer currentAmount,
                            @ModelAttribute("skipUsed") Boolean skipUsed,
                            @ModelAttribute("fiftyFiftyUsed") Boolean fiftyFiftyUsed,
                            Model model) {
        List<Question> questions = gameService.getQuestionsGame();
        model.addAttribute("questions", questions);
        model.addAttribute("question", questions.get(currentQuestionIndex));
        model.addAttribute("questionNumber", currentQuestionIndex + 1);
        model.addAttribute("totalQuestions", questions.size());
        model.addAttribute("currentAmount", currentAmount);
        model.addAttribute("skipUsed", skipUsed);
        model.addAttribute("fiftyFiftyUsed", fiftyFiftyUsed);
        return "game";
    }

    @RequestMapping("/game-answer")
    public String processAnswer(@RequestParam int answerId,
                                @ModelAttribute("employee") Employee employee,
                                @ModelAttribute("currentQuestionIndex") Integer currentQuestionIndex,
                                @ModelAttribute("questions") List<Question> questions,
                                @ModelAttribute("currentAmount") Integer currentAmount,
                                @ModelAttribute("fiftyFiftyUsed") Boolean fiftyFiftyUsed,
                                Model model) {

        if (!gameService.isPositiveAnswer(answerId)) {
            model.addAttribute("showResultModal", true);
            model.addAttribute("isWin", false);
            model.addAttribute("question", questions.get(currentQuestionIndex));
            model.addAttribute("questionNumber", currentQuestionIndex + 1);
            model.addAttribute("totalQuestions", questions.size());
            model.addAttribute("fiftyFiftyUsed", fiftyFiftyUsed);
            return "game";
        } else {
            // Правильный ответ - переходим к следующему вопросу
            currentQuestionIndex++;
            if (currentQuestionIndex < questions.size()) {
                model.addAttribute("currentQuestionIndex", currentQuestionIndex);
                model.addAttribute("question", questions.get(currentQuestionIndex));
                model.addAttribute("questionNumber", currentQuestionIndex + 1);
                model.addAttribute("totalQuestions", questions.size());
                currentAmount += 100000;
                model.addAttribute("currentAmount", currentAmount);
                model.addAttribute("fiftyFiftyUsed", fiftyFiftyUsed);
                return "game";
            } else {
                model.addAttribute("showResultModal", true);
                model.addAttribute("isWin", true);
                model.addAttribute("fiftyFiftyUsed", fiftyFiftyUsed);
                return "game";
            }
        }
    }

    @RequestMapping("/game-skip")
    public String skipQuestion(@ModelAttribute("employee") Employee employee,
                               @ModelAttribute("currentQuestionIndex") Integer currentQuestionIndex,
                               @ModelAttribute("questions") List<Question> questions,
                               @ModelAttribute("currentAmount") Integer currentAmount,
                               @ModelAttribute("skipUsed") Boolean skipUsed,
                               @ModelAttribute("fiftyFiftyUsed") Boolean fiftyFiftyUsed,
                               Model model) {

        model.addAttribute("skipUsed", true);

        currentQuestionIndex++;
        if (currentQuestionIndex < questions.size()) {
            model.addAttribute("currentQuestionIndex", currentQuestionIndex);
            model.addAttribute("question", questions.get(currentQuestionIndex));
            model.addAttribute("questionNumber", currentQuestionIndex + 1);
            model.addAttribute("totalQuestions", questions.size());
            model.addAttribute("currentAmount", currentAmount);
            model.addAttribute("fiftyFiftyUsed", fiftyFiftyUsed);
            return "game";
        } else {
            model.addAttribute("showResultModal", true);
            model.addAttribute("isWin", true);
            model.addAttribute("fiftyFiftyUsed", fiftyFiftyUsed);
            return "game";
        }
    }

    @RequestMapping("/game-fifty")
    public String useFiftyFifty(@ModelAttribute("employee") Employee employee,
                                @ModelAttribute("currentQuestionIndex") Integer currentQuestionIndex,
                                @ModelAttribute("questions") List<Question> questions,
                                @ModelAttribute("currentAmount") Integer currentAmount,
                                @ModelAttribute("fiftyFiftyUsed") Boolean fiftyFiftyUsed,
                                @ModelAttribute("skipUsed") Boolean skipUsed,
                                Model model) {

        // Устанавливаем, что подсказка 50/50 использована
        model.addAttribute("fiftyFiftyUsed", true);

        // Получаем текущий вопрос
        Question currentQuestion = questions.get(currentQuestionIndex);

        // Логика для удаления двух неправильных ответов
        List<Integer> incorrectAnswers = new ArrayList<>();
        for (Answer answer : currentQuestion.getAnswers()) {
            if (!answer.isRight()) {
                incorrectAnswers.add(answer.getId());
            }
        }

        // Перемешиваем неправильные ответы и оставляем только один
        Collections.shuffle(incorrectAnswers);
        List<Integer> disabledAnswers = incorrectAnswers.subList(0, 2);

        model.addAttribute("disabledAnswers", disabledAnswers);
        model.addAttribute("question", currentQuestion);
        model.addAttribute("questionNumber", currentQuestionIndex + 1);
        model.addAttribute("totalQuestions", questions.size());
        model.addAttribute("currentAmount", currentAmount);
        model.addAttribute("skipUsed", skipUsed);

        return "game";
    }

    @RequestMapping(value = "/game-end", method = RequestMethod.POST)
    public String endGame(@ModelAttribute("employee") Employee employee,
                          @RequestParam(value = "isWin", required = false) Boolean isWin,
                          @ModelAttribute("currentAmount") Integer currentAmount,
                          Model model) {
        // Сбрасываем индекс вопроса и флаги использования подсказок
        model.addAttribute("skipUsed", false);
        model.addAttribute("fiftyFiftyUsed", false);
        model.addAttribute("currentQuestionIndex", 0);

        // Если игра выиграна - обновляем статистику
        if (isWin != null && isWin) {
            Employee dbEmployee = employeeService.getEmployee(employee.getName(), employee.getPassword());
            if (dbEmployee != null) {
                int newWins = dbEmployee.getWins() + 1;
                employeeService.updateEmployee(dbEmployee.getId(), dbEmployee.getCapital() + currentAmount, newWins);

                // Обновляем данные в сессии
                Employee updatedEmployee = employeeService.getEmployeeById(dbEmployee.getId());
                model.addAttribute("employee", updatedEmployee);
                model.addAttribute("currentAmount", 100000);
            }
        } else if (isWin != null && !isWin) {
            Employee dbEmployee = employeeService.getEmployee(employee.getName(), employee.getPassword());
            if (dbEmployee != null) {
                int newWins = dbEmployee.getWins();
                employeeService.updateEmployee(dbEmployee.getId(), 0, newWins);

                // Обновляем данные в сессии
                Employee updatedEmployee = employeeService.getEmployeeById(dbEmployee.getId());
                model.addAttribute("employee", updatedEmployee);
                model.addAttribute("currentAmount", 100000);
            }
        }

        return "room";
    }
}
