package fr.diginamic.validation.validators;

import fr.diginamic.validation.annotations.NullableTogether;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

import java.time.LocalDate;
import java.util.Objects;

public class NullableTogetherValidator implements ConstraintValidator<NullableTogether, Object> {
    private String field1;
    private String field2;
    private String message;
    @Override
    public void initialize(NullableTogether constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        this.field1 = constraintAnnotation.firstField();
        this.field2 = constraintAnnotation.secondField();
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        var _field1 = new BeanWrapperImpl(value).getPropertyValue(field1);
        var _field2 = new BeanWrapperImpl(value).getPropertyValue(field2);
        if (_field2 == null) {
            return true;
        }
        if (_field1 == null) {
            populateAnnotationContextIfInvalid(context);
            return false;
        }
        return true;
    }
    private void populateAnnotationContextIfInvalid(ConstraintValidatorContext ctx){
        ctx.disableDefaultConstraintViolation();
        ctx
                .buildConstraintViolationWithTemplate(message)
                .addPropertyNode(field1)
                .addConstraintViolation();
    }
}
