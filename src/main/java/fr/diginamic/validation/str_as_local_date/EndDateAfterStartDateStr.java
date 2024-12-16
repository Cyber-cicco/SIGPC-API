package fr.diginamic.validation.str_as_local_date;

import fr.diginamic.validation.validators.EndDateAfterStartDateStrValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Constraint(validatedBy = EndDateAfterStartDateStrValidator.class)
// @Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EndDateAfterStartDateStr {

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
    EndDateAfterStartDateStr[] value();
  }
}
