package com.godel.mastery.validation;

import com.godel.mastery.annotation.MinAge;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

/**
 * Realise of MinAge annotation.
 */
public class MinAgeConstraintValidator implements ConstraintValidator<MinAge, LocalDate> {

    private int annotationMinAge;

    @Override
    public void initialize(MinAge minAge) {
        this.annotationMinAge = minAge.value();
    }

    @Override
    public boolean isValid(LocalDate minAge, ConstraintValidatorContext constraintValidatorContext) {
        if (minAge == null) {
            return true;
        }
        LocalDate now = LocalDate.now();
        LocalDate toDateOfBirth = now.minusYears(annotationMinAge);
        return minAge.isBefore(toDateOfBirth);
    }
}