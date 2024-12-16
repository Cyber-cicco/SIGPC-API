package fr.diginamic.validation.validators;

import fr.diginamic.utils.ValidationUtils;
import fr.diginamic.validation.annotations.ValidPassword;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {

    private String message;
    @Override
    public void initialize(ValidPassword constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        this.message = constraintAnnotation.message();
    }

    /**
     * Méthode pour vérfier que le mot de passe répond aux contraintes suivantes:
     *  - possède un caractère en minuscule
     *  - possède un caractère en majuscule
     *  - possède un chiffre
     *  - possède un caractère spécial
     * @param password la chaine à vérifier
     */
    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        boolean hasUpperCase = false;
        boolean hasLowerCase = false;
        boolean hasSpecialChar = false;
        boolean hasNumber = false;
        for (int i = 0; i < password.length(); i++) {
            if (!hasNumber && Character.isDigit(password.charAt(i))) {
                hasNumber = true;
                continue;
            }
            if (!hasUpperCase && Character.isUpperCase(password.charAt(i))) {
                hasUpperCase = true;
                continue;
            }
            if (!hasLowerCase && Character.isLowerCase(password.charAt(i))) {
                hasLowerCase = true;
                continue;
            }
            if (!hasSpecialChar && Character.toString(password.charAt(i)).matches("[^A-Za-z0-9 ]")) {
                hasSpecialChar = true;
            }
        }

        if (hasUpperCase && hasNumber && hasLowerCase && hasSpecialChar) {
            return true;
        }
        String message = ValidationUtils.PASSWORD_INVALID;
        if (!hasNumber) {
            message += ValidationUtils.PASSWORD_NUMBER;
        }
        if (!hasLowerCase) {
            message += ValidationUtils.PASSWORD_LOWER_CASE;
        }
        if (!hasUpperCase) {
            message += ValidationUtils.PASSWORD_UPPER_CASE;
        }
        if (!hasSpecialChar) {
            message += ValidationUtils.PASSWORD_SPECIAL_CHAR;
        }
        context.disableDefaultConstraintViolation();
        context
                .buildConstraintViolationWithTemplate(message)
                .addConstraintViolation();
        return false;
    }
}
