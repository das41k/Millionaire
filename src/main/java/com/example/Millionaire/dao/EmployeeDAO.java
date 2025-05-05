package com.example.Millionaire.dao;

import com.example.Millionaire.entity.Employee;

public interface EmployeeDAO {
    void addEmployee(Employee employee);
    Employee getEmployee(String name, String password);
    void updateEmployee(int employeeId, Integer employeeCapital, int employeeWins);
    Employee getEmployeeById(int employeeId);
}
