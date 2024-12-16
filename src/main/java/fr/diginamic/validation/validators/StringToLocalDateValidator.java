package fr.diginamic.validation.validators;

import fr.diginamic.shared.utils.StringToLocalDateUtils;
import fr.diginamic.validation.annotations.StringToLocalDate;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.format.DateTimeFormatter;

public class StringToLocalDateValidator implements ConstraintValidator<StringToLocalDate, String> {

  private String localDateFormat;

  // TODO : supprimer code commenté
  //  private boolean isValidDate(String dateString) {
  //    DateTimeFormatter dateFormatter =
  //        DateTimeFormatter.ofPattern(localDateFormat).withResolverStyle(ResolverStyle.STRICT);
  //
  //    try {
  //      LocalDate.parse(dateString, dateFormatter);
  //    } catch (DateTimeParseException e) {
  //      return false;
  //    } catch (Exception e) {
  //      // TODO : utiliser un logger plutôt
  //      System.err.println("Other exception");
  //      return false;
  //    }
  //    return true;
  //  }

  @Override
  public void initialize(StringToLocalDate constraintAnnotation) {
    ConstraintValidator.super.initialize(constraintAnnotation);
    this.localDateFormat = constraintAnnotation.localDateFormat();
  }

  @Override
  public boolean isValid(String dateStr, ConstraintValidatorContext constraintValidatorContext) {
    DateTimeFormatter dateFormatter = StringToLocalDateUtils.getDateFormatter(localDateFormat);
    return StringToLocalDateUtils.isValidDate(dateStr, dateFormatter);

    //    return isValidDate(dateStr);
  }
}
