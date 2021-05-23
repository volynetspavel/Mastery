package com.godel.mastery.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Employee is an entity of employee.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    private Integer employeeId;
    private String firstName;
    private String lastName;
    private Integer departmentId;
    private String jobTitle;
    private String gender;
    private LocalDate dateOfBirth;
}