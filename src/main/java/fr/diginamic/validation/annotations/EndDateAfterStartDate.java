package fr.diginamic.validation.annotations;

import fr.diginamic.validation.validators.EndDateAfterStartDateValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = EndDateAfterStartDateValidator.class)
// @Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EndDateAfterStartDate {

  String message() default "End date must be after start date";

  String localDateFormat() default "uuuu-MM-dd";

  String startDateField();

  String endDateField();

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  //  @Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
  @Target({ElementType.TYPE})
  @Retention(RetentionPolicy.RUNTIME)
  @Documented
  @interface List {
    EndDateAfterStartDate[] value();
  }
}
