package com.godel.mastery.validation;

import java.time.LocalDate;

/**
 * Class is used for validating employee entity.
 */
public class EmployeeValidation {

    private static final int AGE_FROM = 18;
    private static final int AGE_TO = 60;

    public static boolean isDateOfBirthValid(LocalDate dateOfBirth) {
        LocalDate now = LocalDate.now();
        LocalDate toDateOfBirth = now.minusYears(AGE_FROM);
        LocalDate fromDateOfBirth = now.minusYears(AGE_TO);

        return dateOfBirth.isAfter(fromDateOfBirth) && dateOfBirth.isBefore(toDateOfBirth);
    }
}
