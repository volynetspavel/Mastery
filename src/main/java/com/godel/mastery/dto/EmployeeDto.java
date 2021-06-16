package com.godel.mastery.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.godel.mastery.annotation.MaxAge;
import com.godel.mastery.annotation.MinAge;
import com.godel.mastery.service.Insertable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Class-wrapper for employee.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Null(message = "Id must be null.")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer id;
    @Pattern(regexp = "[A-Za-z]+", message = "First name of employee must be according [A-Za-z]+.")
    @NotBlank(groups = Insertable.class, message = "First name must not be blank.")
    private String firstName;
    @Pattern(regexp = "[A-Za-z \\-]+", message = "Last name of employee must be according [A-Za-z \\-].")
    @NotBlank(groups = Insertable.class, message = "Last name must not be blank.")
    private String lastName;
    @Min(value = 1, message = "Department_Id must be greater than 1.")
    @NotNull(groups = Insertable.class, message = "Id must not be null.")
    private Integer departmentId;
    @Pattern(regexp = "[A-Za-z \\-]+", message = "Title of title must be according [A-Za-z \\-].")
    @NotBlank(groups = Insertable.class, message = "Title of job must not be blank.")
    private String jobTitle;
    @Pattern(regexp = "(male)|(female)", message = "Gender of employee must be male or female.")
    @NotBlank(groups = Insertable.class, message = "Gender of employee must not be blank.")
    private String gender;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @MinAge(value = 18, message = "Minimum age 18 years old.")
    @MaxAge(value = 60, message = "Maximum age 60 years old.")
    @NotNull(groups = Insertable.class, message = "Date of birth must not be null.")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate dateOfBirth;
}
