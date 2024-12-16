package fr.diginamic.validation.str_as_local_date;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = LocalDateValidatorStr.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CustomLocalDateStr {

  String message() default "Le format de la date est invalide";

  String localDateFormat() default "uuuu-MM-dd";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
