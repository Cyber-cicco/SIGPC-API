package fr.diginamic.validation.annotations;

import fr.diginamic.validation.validators.FieldMustMatchValidator;
import fr.diginamic.validation.validators.NullableTogetherValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Constraint(validatedBy = NullableTogetherValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Repeatable(value = NullableTogether.List.class)
public @interface NullableTogether {

    String message() default "If field 2 is not null, field 1 should not be";

    String firstField();
    String secondField();
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        NullableTogether[] value();
    }
}

