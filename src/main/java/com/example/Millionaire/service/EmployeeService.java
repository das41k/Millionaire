package com.example.Millionaire.service;

import com.example.Millionaire.entity.Employee;

public interface EmployeeService {
    void addEmployee(Employee employee);
    boolean checkPositiveData(String name, String password);
}
