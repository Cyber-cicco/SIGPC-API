package fr.diginamic.validation.str_as_local_date;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class LocalDateValidatorStr implements ConstraintValidator<CustomLocalDateStr, String> {

  private String localDateFormat;

  private boolean isValidDate(String dateString) {
    try {
      DateTimeFormatter dateFormatter =
          DateTimeFormatter.ofPattern(localDateFormat).withResolverStyle(ResolverStyle.STRICT);
      LocalDate.parse(dateString, dateFormatter);
    } catch (DateTimeParseException e) {
      return false;
    } catch (Exception e) {
      System.err.println("Other exception");
      return false;
    }
    return true;
  }

  @Override
  public void initialize(CustomLocalDateStr constraintAnnotation) {
    ConstraintValidator.super.initialize(constraintAnnotation);
    this.localDateFormat = constraintAnnotation.localDateFormat();
    //    this.dateFormatter = DateTimeFormatter.ofPattern(constraintAnnotation.localDateFormat());
  }

  @Override
  public boolean isValid(String dateStr, ConstraintValidatorContext constraintValidatorContext) {
    return isValidDate(dateStr);
  }
}
