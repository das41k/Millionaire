package com.example.Millionaire.dao;

import com.example.Millionaire.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeDAOImpl implements EmployeeDAO {

    @Autowired
    private EntityManager entityManager;


    @Override
    public void addEmployee(Employee employee) {
        Session session = entityManager.unwrap(Session.class);
        session.save(employee);
    }

    @Override
    public Employee getEmployee(String name, String password) {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery("from Employee " + "where name =:name AND password =:password");
        query.setParameter("name", name);
        query.setParameter("password", password);
        List<Employee> results = query.getResultList();
        return results.isEmpty() ? null : results.get(0);
    }
}
