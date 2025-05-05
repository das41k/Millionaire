package com.example.Millionaire.service;

import com.example.Millionaire.dao.EmployeeDAO;
import com.example.Millionaire.entity.Employee;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDAO employeeDAO;

    @Override
    @Transactional
    public void addEmployee(Employee employee) {
        employeeDAO.addEmployee(employee);
    }

    @Override
    @Transactional
    public boolean checkPositiveData(String name, String password) {
        Employee employee = employeeDAO.getEmployee(name, password);
        return employee != null;
    }

    @Override
    @Transactional
    public void updateEmployee(int employeeId, Integer employeeCapital, int employeeWins) {
        employeeDAO.updateEmployee(employeeId, employeeCapital, employeeWins);
    }

    @Override
    @Transactional
    public Employee getEmployee(String name, String password) {
        Employee employee = employeeDAO.getEmployee(name, password);
        return employee;
    }

    @Override
    @Transactional
    public Employee getEmployeeById(int employeeId) {
        return employeeDAO.getEmployeeById(employeeId);
    }


}
