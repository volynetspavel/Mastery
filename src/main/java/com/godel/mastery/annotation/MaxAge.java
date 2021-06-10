package com.godel.mastery.annotation;

import com.godel.mastery.validation.MaxAgeConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
@Constraint(validatedBy = MaxAgeConstraintValidator.class)
public @interface MaxAge {

    String message() default "{com.godel.mastery.annotation.MaxAge.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int value();
}