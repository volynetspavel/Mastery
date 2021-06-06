package com.godel.mastery.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.godel.mastery.validation.CustomLocalDateDeserializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.Null;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Class-wrapper for employee.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {

    @Null(message = "Id must be null.")
    @ApiModelProperty(value = "Id of employee.")
    private Integer id;
    @Pattern(regexp = "[A-Za-z]+", message = "First name of employee must be according [A-Za-z]+.")
    private String firstName;
    @Pattern(regexp = "[A-Za-z \\-]+", message = "Last name of employee must be according [A-Za-z \\-].")
    private String lastName;
    @Min(value = 1, message = "Department_Id must be greater than 1.")
    private Integer departmentId;
    @Pattern(regexp = "[A-Za-z \\-]+", message = "Title of title must be according [A-Za-z \\-].")
    private String jobTitle;
    @Pattern(regexp = "(male)|(female)", message = "Gender of employee must be male or female.")
    private String gender;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = CustomLocalDateDeserializer.class)
    private LocalDate dateOfBirth;
}
