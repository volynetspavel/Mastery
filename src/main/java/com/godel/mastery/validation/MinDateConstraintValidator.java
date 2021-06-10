package com.godel.mastery.validation;

import com.godel.mastery.annotation.MinDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

/**
 * Realise of MinDate annotation.
 */
public class MinDateConstraintValidator implements ConstraintValidator<MinDate, LocalDate> {

    private LocalDate annotationMinDate;

    @Override
    public void initialize(MinDate minDate) {
        this.annotationMinDate = LocalDate.parse(minDate.value());
    }

    @Override
    public boolean isValid(LocalDate minDate, ConstraintValidatorContext constraintValidatorContext) {
        if (minDate == null) {
            return true;
        }
        return minDate.isAfter(annotationMinDate);
    }
}
