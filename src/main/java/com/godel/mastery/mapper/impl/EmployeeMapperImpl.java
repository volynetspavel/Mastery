package com.godel.mastery.mapper.impl;

import com.godel.mastery.dto.EmployeeDto;
import com.godel.mastery.mapper.Mapper;
import com.godel.mastery.model.Employee;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper for employee entity.
 */
@Component
public class EmployeeMapperImpl implements Mapper {

    @Override
    public Employee toEntity(EmployeeDto dto) {
        if (dto == null) {
            return null;
        }
        Employee employee = new Employee();
        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        employee.setDepartmentId(dto.getDepartmentId());
        employee.setJobTitle(dto.getJobTitle());
        employee.setGender(dto.getGender());
        employee.setDateOfBirth(dto.getDateOfBirth());

        return employee;
    }

    @Override
    public EmployeeDto toDto(Employee entity) {
        if (entity == null) {
            return null;
        }
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(entity.getEmployeeId());
        employeeDto.setFirstName(entity.getFirstName());
        employeeDto.setLastName(entity.getLastName());
        employeeDto.setDepartmentId(entity.getDepartmentId());
        employeeDto.setJobTitle(entity.getJobTitle());
        employeeDto.setGender(entity.getGender());
        employeeDto.setDateOfBirth(entity.getDateOfBirth());

        return employeeDto;
    }

    @Override
    public List<EmployeeDto> toDtoList(List<Employee> entities) {
        if (entities == null) {
            return new ArrayList<>();
        }
        return entities.stream()
                .map(employee -> toDto(employee))
                .collect(Collectors.toList());
    }
}
