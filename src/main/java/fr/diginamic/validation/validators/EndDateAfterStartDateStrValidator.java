package fr.diginamic.validation.validators;

import static fr.diginamic.shared.utils.StringToLocalDateUtils.getDateFormatter;
import static fr.diginamic.shared.utils.StringToLocalDateUtils.parseDate;

import fr.diginamic.validation.str_as_local_date.EndDateAfterStartDateStr;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.BeanWrapperImpl;

public class EndDateAfterStartDateStrValidator
    implements ConstraintValidator<EndDateAfterStartDateStr, Object> {

  private String localDateFormat;
  private String startDateField;
  private String endDateField;
  private String message;

  @Override
  public void initialize(EndDateAfterStartDateStr constraintAnnotation) {
    //    ConstraintValidator.super.initialize(constraintAnnotation);
    this.localDateFormat = constraintAnnotation.localDateFormat();
    this.startDateField = constraintAnnotation.startDateField();
    this.endDateField = constraintAnnotation.endDateField();
    this.message = constraintAnnotation.message();
  }

  @Override
  public boolean isValid(Object obj, ConstraintValidatorContext context) {
    String startDateStr = (String) new BeanWrapperImpl(obj).getPropertyValue(startDateField);
    String endDateStr = (String) new BeanWrapperImpl(obj).getPropertyValue(endDateField);

    //    System.out.println("obj = " + obj);
    System.out.println("startDateStr = " + startDateStr);
    System.out.println("endDateStr = " + endDateStr);

    final DateTimeFormatter dateFormatter = getDateFormatter(localDateFormat);
    LocalDate startDate = parseDate(startDateStr, dateFormatter);
    LocalDate endDate = endDateStr == null ? null : parseDate(endDateStr, dateFormatter);
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
