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
        LocalDate from = now.minusYears(AGE_FROM);
        LocalDate to = now.minusYears(AGE_TO);

        return dateOfBirth.isAfter(from) && dateOfBirth.isBefore(to);
    }
}
