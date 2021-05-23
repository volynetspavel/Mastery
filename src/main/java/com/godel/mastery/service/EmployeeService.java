package com.godel.mastery.service;

import com.godel.mastery.dto.EmployeeDto;

import java.util.List;

/**
 * This class is a layer for interacting with EmployeeDao.
 */
public interface EmployeeService {

    EmployeeDto insert(EmployeeDto newEmployee);

    EmployeeDto update(EmployeeDto employee);

    void delete(Integer id);

    EmployeeDto findById(Integer id);

    List<EmployeeDto> findAll();
}
