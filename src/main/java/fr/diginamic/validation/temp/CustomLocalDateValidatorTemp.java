package fr.diginamic.validation.temp;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CustomLocalDateValidatorTemp implements DateValidator {

  private final DateTimeFormatter dateFormatter;

  public CustomLocalDateValidatorTemp(DateTimeFormatter dateFormatter) {
    this.dateFormatter = dateFormatter;
  }

  @Override
  public boolean isValid(String dateStr) {
    try {
      LocalDate.parse(dateStr, dateFormatter);
    } catch (DateTimeParseException e) {
      return false;
    }
    return true;
  }
}
