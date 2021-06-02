package com.godel.mastery.service.impl;

import com.godel.mastery.dao.EmployeeDao;
import com.godel.mastery.dto.EmployeeDto;
import com.godel.mastery.exception.ResourceNotFoundException;
import com.godel.mastery.mapper.Mapper;
import com.godel.mastery.model.Employee;
import com.godel.mastery.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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
        Employee employee = employeeMapper.toEntity(newEmployee);
        Employee newEmployeeFromDb = employeeDao.save(employee);
        return employeeMapper.toDto(newEmployeeFromDb);
    }

    @Override
    @Transactional
    public EmployeeDto update(EmployeeDto updatedEmployeeDto) {
        Integer idUpdatedEmployee = updatedEmployeeDto.getId();
        Employee employeeFromDb = employeeDao.findById(idUpdatedEmployee)
                .orElseThrow(() -> new ResourceNotFoundException("Requested resource not found (id = "
                        + idUpdatedEmployee + ")"));

        String firstName = updatedEmployeeDto.getFirstName();
        if (firstName != null) {
            employeeFromDb.setFirstName(firstName);
        }
        String lastName = updatedEmployeeDto.getLastName();
        if (lastName != null) {
            employeeFromDb.setLastName(lastName);
        }
        Integer departmentId = updatedEmployeeDto.getDepartmentId();
        if (departmentId != null) {
            employeeFromDb.setDepartmentId(departmentId);
        }
        String jobTitle = updatedEmployeeDto.getJobTitle();
        if (jobTitle != null) {
            employeeFromDb.setJobTitle(jobTitle);
        }
        String gender = updatedEmployeeDto.getGender();
        if (gender != null) {
            employeeFromDb.setGender(gender);
        }
        LocalDate dateOfBirth = updatedEmployeeDto.getDateOfBirth();
        if (dateOfBirth != null) {
            employeeFromDb.setDateOfBirth(dateOfBirth);
        }

        return employeeMapper.toDto(employeeFromDb);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        Employee employee = employeeDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Requested resource not found (id = " + id + ")"));
        employeeDao.delete(employee);
    }

    @Override
    public EmployeeDto findById(Integer id) {
        Employee employee = employeeDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Requested resource not found (id = " + id + ")"));
        return employeeMapper.toDto(employee);
    }

    @Override
    public List<EmployeeDto> findAll() {
        return employeeMapper.toDtoList(employeeDao.findAll());
    }
}
