package com.godel.mastery.controller;

import com.godel.mastery.dto.EmployeeDto;
import com.godel.mastery.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

/**
 * Class is used to send requests from the client to the service layer for employee entity.
 */
@RestController
@RequestMapping("/employees")
@Validated
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public EmployeeDto insert(@RequestBody @Valid EmployeeDto employeeDto) {
        return employeeService.insert(employeeDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public EmployeeDto update(@PathVariable("id") @Min(value = 1) int id,
                              @RequestBody @Valid EmployeeDto employeeDto) {
        employeeDto.setId(id);
        return employeeService.update(employeeDto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") @Min(value = 1) int id) {
        employeeService.delete(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public EmployeeDto findById(@PathVariable("id") @Min(value = 1) int id) {
        return employeeService.findById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<EmployeeDto> findAll() {
        return employeeService.findAll();
    }
}
