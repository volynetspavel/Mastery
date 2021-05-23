package com.godel.mastery.validation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;

class EmployeeValidationTest {

    @DisplayName("Testing method isDateOfBirthValid() on positive result")
    @Test
    void isDateOfBirthValidSuccessTest() {
        LocalDate dateOfBirth = LocalDate.parse("1992-08-12");
        Assertions.assertTrue(EmployeeValidation.isDateOfBirthValid(dateOfBirth));
    }

    @DisplayName("Testing method isDateOfBirthValid() on negative result")
    @ParameterizedTest
    @ValueSource(strings = {"2012-08-12", "1915-10-23"})
    void isDateOfBirthValidUnsuccessTest(String dateOfBirth) {
        Assertions.assertFalse(EmployeeValidation.isDateOfBirthValid(LocalDate.parse(dateOfBirth)));
    }
}
