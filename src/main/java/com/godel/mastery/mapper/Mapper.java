package com.godel.mastery.mapper;

import com.godel.mastery.dto.EmployeeDto;
import com.godel.mastery.model.Employee;

import java.util.List;

/**
 * Interface for transferring between DTO-class and entity class.
 */
public interface Mapper {

    Employee toEntity(EmployeeDto dto);

    EmployeeDto toDto(Employee entity);

    List<EmployeeDto> toDtoList(List<Employee> entities);

}
