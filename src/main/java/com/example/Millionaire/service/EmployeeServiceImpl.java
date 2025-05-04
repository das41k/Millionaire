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

}
