package fr.diginamic.validation.deserializers;

import static fr.diginamic.shared.utils.StringToLocalDateUtils.getDateFormatter;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import fr.diginamic.exception.ApiException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class LocalDateDeserializer extends StdDeserializer<LocalDate> {

  private static final DateTimeFormatter formatter = getDateFormatter("uuuu" + "-MM-dd");

  protected LocalDateDeserializer() {
    this(null);
  }

  protected LocalDateDeserializer(Class<?> vc) {
    super(vc);
  }

  @Override
  public LocalDate deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
      throws IOException, JacksonException {

    String date = jsonParser.getText();
    try {
      return LocalDate.parse(date, formatter);
    } catch (DateTimeParseException e) {
      throw new ApiException("Date invalide");
    }
  }
}
