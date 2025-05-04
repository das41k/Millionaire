package com.example.Millionaire.controller;

import com.example.Millionaire.entity.Employee;
import com.example.Millionaire.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/")
    public String showAuth(Model model, @ModelAttribute("errorMessage") String errorMessage) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
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
            redirectAttributes.addFlashAttribute("employee", employee);
            return "redirect:/register";
        }
        else if ("Вход".equals(action)) {
            redirectAttributes.addFlashAttribute("employee", employee);
            return "redirect:/login";
        }

        return "home";
    }

    @RequestMapping("/register")
    public String register(@ModelAttribute("employee") Employee employee, RedirectAttributes redirectAttributes) {
        boolean resultCheck = employeeService.checkPositiveData(employee.getName(), employee.getPassword());
        if (!resultCheck) {
            employeeService.addEmployee(employee);
            return "room";
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Данный пользователь уже есть в системе!");
            return "redirect:/";
        }
    }

    @RequestMapping("/login")
    public String login(@ModelAttribute("employee") Employee employee, RedirectAttributes redirectAttributes) {
        boolean resultCheck = employeeService.checkPositiveData(employee.getName(), employee.getPassword());
        if (resultCheck) {
            return "room";
        } else {
            System.out.println(employee.getName() + employee.getPassword());
            redirectAttributes.addFlashAttribute("errorMessage", "Неверный логин или пароль");
            return "redirect:/";
        }
    }
}
