package fr.diginamic.services;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import fr.diginamic.dto.CompteDto;
import fr.diginamic.dto.LoginDto;
import fr.diginamic.dto.PasswordChangeDto;
import fr.diginamic.entities.TentativeChangementMdp;
import fr.diginamic.entities.Utilisateur;
import fr.diginamic.entities.enums.RoleEnum;
import fr.diginamic.exception.UnauthorizedException;
import fr.diginamic.repository.TentativeSupressionMdpRepository;
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

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Validated
public class UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;
    private final TentativeSupressionMdpRepository tentativeSupressionMdpRepository;
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

    /**
     * Change le mot de passe de l'utilisateur ayant effectué une demande
     * @param uuid l'identifiant de la demande
     * @param passwordChangeDto l'objet contenant le mot de passe
     * @throws ValidationException
     */
    @Transactional
    public void changePassword(String uuid, @Valid PasswordChangeDto passwordChangeDto) throws ValidationException {
        // Récupération de la tentative, et levée d'erreur si non trouvée
        var link = UUID.fromString(uuid);
        var passwordChange = tentativeSupressionMdpRepository.findByLinkAndActiveUntilAfter(link, LocalDateTime.now())
                .orElseThrow(EntityNotFoundException::new);

        validationUtils.throwIfTrue(!passwordChangeDto.getPassword().equals(passwordChangeDto.getPasswordConf()), ValidationUtils.PASSWORD_CONF_NO_MATCH);
        validatePassword(passwordChangeDto.getPassword());
        var utilisateur = passwordChange.getUtilisateur();
        if (utilisateur == null) {
            throw new EntityNotFoundException("User was not supposed to be null"); // TODO : extraire ça dans une constante
        }
        utilisateur.setPassword(encoder.encode(passwordChangeDto.getPassword()));
        utilisateurRepository.save(utilisateur);
        tentativeSupressionMdpRepository.delete(passwordChange);
    }

    public void removePasswordChangeAttempt(PasswordInfos askMdp) {
        tentativeSupressionMdpRepository.delete(askMdp.tentativeChangementMdp);
    }

    /**
     * Type contenant le mail auquel envoyé le lien ainsi que le lien
     * @param email le mail de l'utilisateur
     * @param tentativeChangementMdp l'objet représentant la tentative de changement de mot de passe
     */
    public record PasswordInfos(String email, TentativeChangementMdp tentativeChangementMdp){}

    /**
     * Permet d'ajouter une demande de tentative de récupération de mot de passe
     * @param loginDto l'object contenant le mail
     * @return le lien du mail et l'object créé en base
     */
    @Transactional
    public PasswordInfos createNewPasswordChangeAttempt(LoginDto loginDto) {
        var utilisateur = utilisateurRepository.findUtilisateurByEmailAndEmailVerified(loginDto.getEmail(), true)
                .orElseThrow(EntityNotFoundException::new);
        var tentativeSuppressionMDP = TentativeChangementMdp.builder()
                .activeUntil(LocalDateTime.now().plusHours(24)) // TODO : extraire dans un fichier de configuration
                .date(LocalDateTime.now())
                .utilisateur(utilisateur)
                .link(UUID.randomUUID())
                .build();
        return new PasswordInfos(
                utilisateur.getEmail(),
                tentativeSupressionMdpRepository.save(tentativeSuppressionMDP)
        );
    }
}
