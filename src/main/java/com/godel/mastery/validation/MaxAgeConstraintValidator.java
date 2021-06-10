package com.godel.mastery.validation;

import com.godel.mastery.annotation.MaxAge;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

/**
 * Realise of MaxAge annotation.
 */
public class MaxAgeConstraintValidator implements ConstraintValidator<MaxAge, LocalDate> {

    private int annotationMaxAge;

    @Override
    public void initialize(MaxAge maxAge) {
        this.annotationMaxAge = maxAge.value();
    }

    @Override
    public boolean isValid(LocalDate maxAge, ConstraintValidatorContext constraintValidatorContext) {
        if (maxAge == null) {
            return true;
        }
        LocalDate now = LocalDate.now();
        LocalDate fromDateOfBirth = now.minusYears(annotationMaxAge);
        return maxAge.isAfter(fromDateOfBirth);
    }
}
