package fr.diginamic.validation.annotations;

import fr.diginamic.validation.validators.StringToLocalDateValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = StringToLocalDateValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface StringToLocalDate {

  String message() default
      "Date '${validatedValue}' invalide. Vérifiez le format attendu "
          + "'{localDateFormat}' et les valeurs saisies";

  String localDateFormat() default "uuuu-MM-dd";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
