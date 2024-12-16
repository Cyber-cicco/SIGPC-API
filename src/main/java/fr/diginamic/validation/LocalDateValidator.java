package fr.diginamic.validation;

import static fr.diginamic.shared.utils.StringToLocalDateUtils.getDateFormatter;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class LocalDateValidator implements ConstraintValidator<CustomLocalDate, LocalDate> {

  private String localDateFormat;

  @Override
  public void initialize(CustomLocalDate constraintAnnotation) {
    ConstraintValidator.super.initialize(constraintAnnotation);
    this.localDateFormat = constraintAnnotation.localDateFormat();
  }

  private boolean isValidDate(LocalDate localDate) {
    DateTimeFormatter dateFormatter = getDateFormatter(localDateFormat);

    try {
      //      sdf.setLenient(false);
      //      sdf.parse(localDate.toString());

      dateFormatter.parse(localDate.toString());
    } catch (DateTimeParseException e) {
      System.err.println("DATE_TIME_PARSE_EXCEPTION");
      return false;
    } catch (Exception e) {
      System.err.println("OTHER EXCEPTION");
      return false;
    }
    return true;
  }

  @Override
  public boolean isValid(LocalDate localDate, ConstraintValidatorContext ctx) {
    return isValidDate(localDate);
  }
}
