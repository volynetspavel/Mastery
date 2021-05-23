package com.godel.mastery.service.impl;

import com.godel.mastery.dao.EmployeeDao;
import com.godel.mastery.dto.EmployeeDto;
import com.godel.mastery.exception.ResourceNotFoundException;
import com.godel.mastery.exception.ValidationException;
import com.godel.mastery.mapper.Mapper;
import com.godel.mastery.model.Employee;
import com.godel.mastery.service.EmployeeService;
import com.godel.mastery.validation.EmployeeValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * This class is an implementation of EmployeeService.
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDao employeeDao;
    private final Mapper employeeMapper;

    @Autowired
    public EmployeeServiceImpl(EmployeeDao employeeDao, Mapper employeeMapper) {
        this.employeeDao = employeeDao;
        this.employeeMapper = employeeMapper;
    }


    @Override
    @Transactional
    public EmployeeDto insert(EmployeeDto newEmployee) {
        if (EmployeeValidation.isDateOfBirthValid(newEmployee.getDateOfBirth())) {
            throw new ValidationException("Date of birth not valid. You cannot create this employee.");
        }
        Employee employee = employeeMapper.toEntity(newEmployee);
        Employee newEmployeeFromDb = employeeDao.insert(employee);
        return employeeMapper.toDto(newEmployeeFromDb);
    }

    @Override
    @Transactional
    public EmployeeDto update(EmployeeDto updatedEmployeeDto) {
        Integer idUpdatedEmployee = updatedEmployeeDto.getId();
        if (employeeDao.findById(idUpdatedEmployee) == null) {
            throw new ResourceNotFoundException("Requested resource not found (id = " + idUpdatedEmployee + ")");
        }
        Employee employee = employeeMapper.toEntity(updatedEmployeeDto);
        employee.setEmployeeId(idUpdatedEmployee);
        employeeDao.update(employee);
        return employeeMapper.toDto(employeeDao.findById(idUpdatedEmployee));
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        if (employeeDao.findById(id) == null) {
            throw new ResourceNotFoundException("Requested resource not found (id = " + id + ")");
        }
        employeeDao.delete(id);
    }

    @Override
    public EmployeeDto findById(Integer id) {
        Employee employee = employeeDao.findById(id);
        if (employee == null) {
            throw new ResourceNotFoundException("Requested resource not found (id = " + id + ")");
        }
        return employeeMapper.toDto(employee);
    }

    @Override
    public List<EmployeeDto> findAll() {
        return employeeMapper.toDtoList(employeeDao.findAll());
    }
}
