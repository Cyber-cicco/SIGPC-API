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
    //    this.localDateFormat = constraintAnnotation.localDateFormat();
    this.startDateField = constraintAnnotation.startDateField();
    this.endDateField = constraintAnnotation.endDateField();
    this.message = constraintAnnotation.message();
  }

  @Override
  public boolean isValid(Object obj, ConstraintValidatorContext context) {
    LocalDate startDate = (LocalDate) new BeanWrapperImpl(obj).getPropertyValue(startDateField);
    LocalDate endDate = (LocalDate) new BeanWrapperImpl(obj).getPropertyValue(endDateField);

    System.out.println("startDate = " + startDate);
    System.out.println("endDate = " + endDate);

    boolean isValid = false;
    if (endDate == null) isValid = true;
    else {
      if (endDate.isAfter(startDate)) isValid = true;
    }

    System.out.println("isValid = " + isValid);

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
