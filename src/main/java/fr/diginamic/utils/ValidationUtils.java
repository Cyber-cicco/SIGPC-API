package fr.diginamic.utils;

import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;

@Component
public class ValidationUtils {

    public static final String PASSWORD_CONF_NO_MATCH = "Le mot de passe et sa confirmation ne correspondent pas";
    public static final String PASSWORD_INVALID = "Le mot de passe n'est pas valide : \n";
    public static final String EMAIL_ALREADY_EXISTS = "Un utilisateur existe déjà avec le même email";
    public static final String PASSWORD_NUMBER = "Le mot de passe doit au moins contenir un chiffre\n";
    public static final String PASSWORD_LOWER_CASE = "Le mot de passe doit au moins contenir une caractère en minuscule\n";
    public static final String PASSWORD_UPPER_CASE = "Le mot de passe doit contenir au moins un caractère en majuscule\n";
    public static final String PASSWORD_SPECIAL_CHAR = "Le mot de passe doit contenir au moins un caractère spécial\n";

    public void throwIfTrue(boolean condition, String message) throws ValidationException {
        if (condition) {
            throw new ValidationException(message);
        }
    }
}
