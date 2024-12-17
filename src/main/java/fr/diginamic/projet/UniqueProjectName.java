package fr.diginamic.projet;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = UniqueProjectNameValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UniqueProjectName {

  String NOM_EXISTANT = "Le projet avec le nom '${validatedValue}' existe déjà";

  String message() default NOM_EXISTANT;

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
