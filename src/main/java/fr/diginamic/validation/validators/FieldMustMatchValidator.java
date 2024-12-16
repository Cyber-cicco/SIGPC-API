package fr.diginamic.validation.validators;

import fr.diginamic.validation.annotations.FieldsMustMatch;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

/**
 * Permet de remplacer une ligne de code par un festival d'annotations
 * en pagaille (mais c'est joli)
 */
public class FieldMustMatchValidator implements ConstraintValidator<FieldsMustMatch, Object> {
    private String field1;
    private String field2;
    private String message;
    @Override
    public void initialize(FieldsMustMatch constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        this.field1 = constraintAnnotation.firstField();
        this.field2 = constraintAnnotation.secondField();
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        String _field1 = (String) new BeanWrapperImpl(value).getPropertyValue(field1);
        String _field2 = (String) new BeanWrapperImpl(value).getPropertyValue(field2);
        var isValid = false;
        if (_field1 == null) {
           if( _field2 != null) {
               populateAnnotationContextIfInvalid(context);
               return false;
           }
           return true;
        }
        isValid = _field1.equals(_field2);
        if (!isValid) {
            populateAnnotationContextIfInvalid(context);
            return false;
        }
        return true;
    }

    private void populateAnnotationContextIfInvalid(ConstraintValidatorContext ctx){
        ctx.disableDefaultConstraintViolation();
        ctx
                .buildConstraintViolationWithTemplate(message)
                .addPropertyNode(field2)
                .addConstraintViolation();
    }

}
