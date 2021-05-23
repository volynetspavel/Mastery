package com.godel.mastery.dao;

import com.godel.mastery.model.Employee;

import java.util.List;

/**
 * This class is a basic class of employee dao-layer for interacting with database.
 */
public interface EmployeeDao {

    Employee insert(Employee newEmployee);

    void update(Employee employee);

    void delete(Integer id);

    Employee findById(Integer id);

    List<Employee> findAll();
}
