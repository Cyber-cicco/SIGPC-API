package fr.diginamic.services;
import fr.diginamic.dto.CompteDto;
import fr.diginamic.utils.ValidationUtils;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import fr.diginamic.dto.UtilisateurTransformer;
import fr.diginamic.repository.UtilisateurRepository;

@Service
@RequiredArgsConstructor
public class UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;
    private final UtilisateurTransformer utilisateurTransformer;
    private final ValidationUtils validationUtils;

    public void createAccount(@Valid CompteDto compteDto)  {
    }

    private void validateAccount(@Valid CompteDto compteDto) throws ValidationException {
        validationUtils.throwIfTrue(!compteDto.getPassword().equals(compteDto.getPasswordConf()), ValidationUtils.PASSWORD_CONF_NO_MATCH);

    }

    private void validatePassword(String password) throws ValidationException {
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
            if (!hasSpecialChar && !password.substring(i, 1).matches("[^A-Za-z0-9 ]")) {
                hasSpecialChar = true;
            }
        }

        if (hasUpperCase && hasNumber && hasLowerCase && hasSpecialChar) {
            return;
        }
        String message = "Le mot de passe n'est pas valide : \n";
        if (!hasNumber) {
            message += "Le mot de passe doit au moins contenir un chiffre";
        }
        if (!hasLowerCase) {
            message += "Le mot de passe doit au moins contenir une caractère en minuscule";
        }
        if (!hasUpperCase) {
            message += "Le mot de passe doit contenir au moins un caractère en majuscule";
        }
        if (!hasSpecialChar) {
            message += "Le mot de passe doit contenir au moins un caractère spécial";
        }
        throw  new ValidationException(message);
    }
}
