package com.godel.mastery.validation;

import com.godel.mastery.annotation.MaxDate;
import com.godel.mastery.annotation.MaxDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

/**
 * Realise of MaxDate annotation.
 */
public class MaxDateConstraintValidator implements ConstraintValidator<MaxDate, LocalDate> {
    
    private LocalDate annotationMaxDate;

    @Override
    public void initialize(MaxDate minDate) {
        this.annotationMaxDate = LocalDate.parse(minDate.value());
    }

    @Override
    public boolean isValid(LocalDate minDate, ConstraintValidatorContext constraintValidatorContext) {
        if (minDate == null) {
            return true;
        }
        return minDate.isBefore(annotationMaxDate);
    }
}
