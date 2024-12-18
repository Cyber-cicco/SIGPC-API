package fr.diginamic.validation.validators;

import fr.diginamic.validation.annotations.EndDateAfterStartDate;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import org.springframework.beans.BeanWrapperImpl;

public class EndDateAfterStartDateValidator
    implements ConstraintValidator<EndDateAfterStartDate, Object> {

  //  private String localDateFormat;
  private String startDateField;
  private String endDateField;
  private String message;

  @Override
  public void initialize(EndDateAfterStartDate constraintAnnotation) {
    ConstraintValidator.super.initialize(constraintAnnotation);
    this.startDateField = constraintAnnotation.startDateField();
    this.endDateField = constraintAnnotation.endDateField();
    this.message = constraintAnnotation.message();
  }

  @Override
  public boolean isValid(Object obj, ConstraintValidatorContext context) {
    LocalDate startDate = (LocalDate) new BeanWrapperImpl(obj).getPropertyValue(startDateField);
    LocalDate endDate = (LocalDate) new BeanWrapperImpl(obj).getPropertyValue(endDateField);

    boolean isValid = false;
    if (endDate == null || startDate == null) return true;
    else {
      if (endDate.isAfter(startDate)) isValid = true;
    }

    if (!isValid) {
      context.disableDefaultConstraintViolation();
      context
          .buildConstraintViolationWithTemplate(message)
          .addPropertyNode(endDateField)
          .addConstraintViolation();
    }

    return isValid;
  }
}
