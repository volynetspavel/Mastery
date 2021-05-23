package com.godel.mastery.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

/**
 * Class-wrapper for employee.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {

    @Null
    private Integer id;
    @Pattern(regexp = "[A-Za-z]+")
    private String firstName;
    @Pattern(regexp = "[A-Za-z \\-]+")
    private String lastName;
    @Min(value = 1)
    private Integer departmentId;
    @Pattern(regexp = "[A-Za-z \\-]+")
    private String jobTitle;
    @Pattern(regexp = "(male)|(female)")
    private String gender;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
}
