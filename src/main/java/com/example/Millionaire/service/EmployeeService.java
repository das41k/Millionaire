package com.example.Millionaire.service;

import com.example.Millionaire.entity.Employee;

public interface EmployeeService {
    void addEmployee(Employee employee);
    boolean checkPositiveData(String name, String password);
    void updateEmployee(int employeeId, Integer employeeCapital, int employeeWins);
    Employee getEmployee(String name, String password);
    Employee getEmployeeById(int employeeId);
}
