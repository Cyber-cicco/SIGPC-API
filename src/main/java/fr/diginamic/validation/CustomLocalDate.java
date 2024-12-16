package fr.diginamic.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Constraint(validatedBy = LocalDateValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CustomLocalDate {

  String message() default "Invalid date format";

  String localDateFormat() default "uuuu-MM-dd";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
