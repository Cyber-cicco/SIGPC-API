package fr.diginamic.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
  //  DateTimeFormatter dateFormatter = DEFAULT_DATE_FORMATTER;

  @Bean
  public ModelMapper modelMapper() {

    //      ModelMapper modelMapper = new ModelMapper();

    //    // Set strict matching strategy to avoid ambiguity
    //    modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    //
    //    Converter<String, LocalDate> stringToLocalDate =
    //        new Converter<String, LocalDate>() {
    //
    //          @Override
    //          public LocalDate convert(MappingContext<String, LocalDate> mappingContext) {
    //            return LocalDate.parse(mappingContext.getSource(), dateFormatter);
    //          }
    //        };
    //
    //    Converter<LocalDate, String> localDateToString =
    //        new Converter<LocalDate, String>() {
    //          @Override
    //          public String convert(MappingContext<LocalDate, String> mappingContext) {
    //            return dateFormatter.format(mappingContext.getSource());
    //            //            return mappingContext.getSource().format(dateFormatter);
    //            //            return mappingContext.getSource().toString();
    //          }
    //        };
    //
    //    modelMapper.addConverter(stringToLocalDate);
    //    modelMapper.addConverter(localDateToString);
    //
    //    return modelMapper;

    return new ModelMapper();
  }
}
