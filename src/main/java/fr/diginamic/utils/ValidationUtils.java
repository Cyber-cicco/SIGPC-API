package fr.diginamic.utils;

import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;

@Component
public class ValidationUtils {

    public static final String PASSWORD_CONF_NO_MATCH = "Le mot de passe et sa confirmation ne correspondent pas";

    public void throwIfTrue(boolean condition, String message) throws ValidationException {
        if (condition) {
            throw new ValidationException(message);
        }
    }
}
