package fr.diginamic.services;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import fr.diginamic.dto.CompteDto;
import fr.diginamic.dto.LoginDto;
import fr.diginamic.entities.Utilisateur;
import fr.diginamic.entities.enums.RoleEnum;
import fr.diginamic.exception.UnauthorizedException;
import fr.diginamic.utils.ValidationUtils;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import fr.diginamic.dto.UtilisateurTransformer;
import fr.diginamic.repository.UtilisateurRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Validated
public class UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;
    private final UtilisateurTransformer utilisateurTransformer;
    private final ValidationUtils validationUtils;
    private final PasswordEncoder encoder;

    /**
     * Crée le compte d'un utilisateur
     * Valide les données entrantes, puis ajoute l'utilisateur dans la base de données
     * en prenant soin de hasher son mot de passe.
     * @param compteDto les informations du compte de l'utilisateur
     * @return l'utilisateur avec son identifiant
     * @throws ValidationException
     * @throws JsonProcessingException
     */
    public Utilisateur createAccount(CompteDto compteDto) throws ValidationException, JsonProcessingException {
        validateAccount(compteDto);
        String password = encoder.encode(compteDto.getPassword());
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String roles = ow.writeValueAsString(List.of(RoleEnum.USER.role));
        var activationLink = UUID.randomUUID();
        Utilisateur nvUtilisateur = utilisateurTransformer.fromCompteToUtilisateur(compteDto, password, roles, activationLink);
        return  utilisateurRepository.save(nvUtilisateur);
    }

    /**
     * Valide les champs n'étant pas validés par Jakarta validation
     * TODO : utiliser les fonctionnalités avancées de Jarkata plutôt que de faire de la validation procédurale
     * @param compteDto les informations du compte de l'utilisateur
     * @throws ValidationException
     */
    private void validateAccount(@Valid CompteDto compteDto) throws ValidationException {
        validationUtils.throwIfTrue(!compteDto.getPassword().equals(compteDto.getPasswordConf()), ValidationUtils.PASSWORD_CONF_NO_MATCH);
        if (utilisateurRepository.existsByEmail(compteDto.getEmail())){
            throw new ValidationException(ValidationUtils.EMAIL_ALREADY_EXISTS);
        }
        validatePassword(compteDto.getPassword());


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
            if (!hasSpecialChar && !password.substring(i, i+1).matches("[^A-Za-z0-9 ]")) {
                hasSpecialChar = true;
            }
        }

        if (hasUpperCase && hasNumber && hasLowerCase && hasSpecialChar) {
            return;
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
        throw  new ValidationException(message);
    }

    @Transactional
    public void deleteAccount(String email) {
        utilisateurRepository.deleteUtilisateurByEmail(email);
    }

    /**
     * Méthode permettant de vérifier l'identité d'un utilisateur en fonction d'un couple
     * email / mot de passe
     * @param loginDto les informations de login
     * @return les informations de l'utilisateur
     * @throws EntityNotFoundException
     */
    public Utilisateur login(@Valid LoginDto loginDto) throws EntityNotFoundException {
        var utilisateur = utilisateurRepository.findUtilisateurByEmail(loginDto.getEmail())
                .orElseThrow(EntityNotFoundException::new);
        if (!encoder.matches(loginDto.getPassword(), utilisateur.getPassword())) {
            throw new UnauthorizedException();
        }
        return utilisateur;
    }
}
