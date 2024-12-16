package fr.diginamic.shared.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class StringToLocalDateUtils {

  public static DateTimeFormatter getDateFormatter(String localDateFormat) {
    DateTimeFormatter formatter;
    try {
      formatter =
          //
          // DateTimeFormatter.ofPattern(localDateFormat).withResolverStyle(ResolverStyle.STRICT);
          DateTimeFormatter.ofPattern(localDateFormat);
    } catch (IllegalArgumentException e) {
      //      formatter =
      // DateTimeFormatter.ofPattern("uuuu-MM-dd").withResolverStyle(ResolverStyle.STRICT);
      formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd");
    }
    return formatter.withResolverStyle(ResolverStyle.STRICT);
    //    return formatter;
  }

  public static boolean isValidDate(String dateString, DateTimeFormatter dateFormatter) {
    try {
      LocalDate.parse(dateString, dateFormatter);
    } catch (DateTimeParseException e) {
      return false;
    } catch (Exception e) {
      System.err.println("Other exception: " + e.getMessage());
      return false;
    }
    return true;
  }

  public static LocalDate parseDate(String dateString, DateTimeFormatter dateFormatter) {
    LocalDate localDate = null;
    try {
      localDate = LocalDate.parse(dateString, dateFormatter);
    } catch (DateTimeParseException e) {
      System.err.println("DateTimeParseException: " + e.getMessage());
    }
    return localDate;
  }
}
