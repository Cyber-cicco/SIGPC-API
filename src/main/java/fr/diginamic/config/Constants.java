package fr.diginamic.config;

import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

public class Constants {
  public static final String API_VERSION_1 = "/api/v1";
  public static final String AUTH_TOKEN = "AUTH-TOKEN";

  public static final DateTimeFormatter DEFAULT_DATE_FORMATTER =
      DateTimeFormatter.ofPattern("uuuu-MM-dd").withResolverStyle(ResolverStyle.STRICT);
}
