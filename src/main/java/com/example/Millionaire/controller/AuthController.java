package com.example.Millionaire.controller;

import com.example.Millionaire.entity.Employee;
import com.example.Millionaire.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@SessionAttributes("employee") // Добавляем аннотацию для хранения employee в сессии
public class AuthController {

    @Autowired
    private EmployeeService employeeService;

    @ModelAttribute("employee") // Инициализация объекта employee для сессии
    public Employee setUpEmployee() {
        return new Employee();
    }

    @RequestMapping("/")
    public String showAuth(Model model, @ModelAttribute("errorMessage") String errorMessage) {
        if (!model.containsAttribute("employee")) {
            model.addAttribute("employee", new Employee());
        }
        if (errorMessage != null) {
            model.addAttribute("errorMessage", errorMessage);
        }
        return "home";
    }

    @RequestMapping("/validData")
    public String checkValidData(@Valid @ModelAttribute("employee") Employee employee,
                                 BindingResult bindingResult,
                                 @RequestParam("action") String action,
                                 RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "home";
        }

        if ("Регистрация".equals(action)) {
            return "redirect:/register";
        } else if ("Вход".equals(action)) {
            return "redirect:/login";
        }

        return "home";
    }

    @RequestMapping("/register")
    public String register(@ModelAttribute("employee") Employee employee,
                           RedirectAttributes redirectAttributes,
                           Model model) {
        boolean resultCheck = employeeService.checkPositiveData(employee.getName(), employee.getPassword());
        if (!resultCheck) {
            // Устанавливаем начальные значения
            employee.setWins(0);
            employee.setCapital(0);
            employeeService.addEmployee(employee);
            // Получаем свежие данные из БД
            Employee dbEmployee = employeeService.getEmployee(employee.getName(), employee.getPassword());
            model.addAttribute("employee", dbEmployee);
            return "room";
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Данный пользователь уже есть в системе!");
            return "redirect:/";
        }
    }

    @RequestMapping("/login")
    public String login(@ModelAttribute("employee") Employee employee,
                        RedirectAttributes redirectAttributes,
                        Model model) {
        boolean resultCheck = employeeService.checkPositiveData(employee.getName(), employee.getPassword());
        if (resultCheck) {
            // Получаем полные данные из БД и сохраняем в сессию
            Employee dbEmployee = employeeService.getEmployee(employee.getName(), employee.getPassword());
            model.addAttribute("employee", dbEmployee);
            return "room";
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Неверный логин или пароль");
            return "redirect:/";
        }
    }
}