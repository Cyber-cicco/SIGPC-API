package fr.diginamic.validation.temp;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class LocalDateValidatorMain {
  private final String localDateFormat;

  public LocalDateValidatorMain(String localDateFormat) {
    this.localDateFormat = localDateFormat;
  }

  private boolean isValidDate(String dateString) {
    try {
      DateTimeFormatter dateFormatter =
          DateTimeFormatter.ofPattern(localDateFormat).withResolverStyle(ResolverStyle.STRICT);
      System.out.println("date formatter: " + dateFormatter);
      LocalDate theDate = LocalDate.parse(dateString, dateFormatter);
      System.out.println("The date parsed is: " + theDate);
    } catch (DateTimeParseException e) {
      System.out.println("DateTimeParseException");
      return false;
    } catch (Exception e) {
      System.out.println("Exception");
      System.out.println(e.getMessage());
      return false;
    }
    return true;
  }

  public static void main(String[] args) {
    String dateStr = "2024-02-29";
    LocalDateValidatorMain validator = new LocalDateValidatorMain("uuuu-MM-dd");
    boolean dateIsValid = validator.isValidDate(dateStr);

    System.out.println("The date '" + dateStr + "' is " + (dateIsValid ? "valid" : "NOT valid"));
  }
}
