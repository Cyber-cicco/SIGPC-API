package fr.diginamic.utilisateur;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import fr.diginamic.entities.TentativeChangementMdp;
import fr.diginamic.entities.Utilisateur;
import fr.diginamic.entities.enums.RoleEnum;
import fr.diginamic.exception.UnauthorizedException;
import fr.diginamic.repository.TentativeSupressionMdpRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import fr.diginamic.repository.UtilisateurRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;
    private final TentativeSupressionMdpRepository tentativeSupressionMdpRepository;
    private final UtilisateurTransformer utilisateurTransformer;
    private final PasswordEncoder encoder;

    /**
     * Crée le compte d'un utilisateur
     * Valide les données entrantes, puis ajoute l'utilisateur dans la base de données
     * en prenant soin de hasher son mot de passe.
     * @param compteDto les informations du compte de l'utilisateur
     * @return l'utilisateur avec son identifiant
     * @throws JsonProcessingException lancée si le rôle n'est pas dans le bon format
     */
    public Utilisateur createAccount(CompteDto compteDto) throws JsonProcessingException {
        String password = encoder.encode(compteDto.getPassword());
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String roles = ow.writeValueAsString(List.of(RoleEnum.USER.role));
        var activationLink = UUID.randomUUID();
        Utilisateur nvUtilisateur = utilisateurTransformer.fromCompteToUtilisateur(compteDto, password, roles, activationLink);
        return  utilisateurRepository.save(nvUtilisateur);
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
     * @throws EntityNotFoundException si l'utilisateur n'a pas pu être trouvé
     */
    public Utilisateur login(LoginDto loginDto) throws EntityNotFoundException {
        var utilisateur = utilisateurRepository.findUtilisateurByEmail(loginDto.getEmail())
                .orElseThrow(EntityNotFoundException::new);
        if (!encoder.matches(loginDto.getPassword(), utilisateur.getPassword())) {
            throw new UnauthorizedException();
        }
        return utilisateur;
    }

    /**
     * Permet de vérfier le mail de l'utilisateur
     * Ne fonctionne que si l'uuid est valide
     * @param uuid identifiant du lien pour l'activation
     */
    @Transactional
    public void activateAccount(String uuid) {
        var link = UUID.fromString(uuid);
        var utilisateur = utilisateurRepository.findByActivationLink(link)
                .orElseThrow(EntityNotFoundException::new);
        utilisateur.setEmailVerified(true);
        utilisateur.setActivationLink(null);
        utilisateurRepository.save(utilisateur);
    }
  
    /**
     * Change le mot de passe de l'utilisateur ayant effectué une demande
     * @param uuid l'identifiant de la demande
     * @param passwordChangeDto l'objet contenant le mot de passe
     */
    @Transactional
    public void changePassword(String uuid, PasswordChangeDto passwordChangeDto) {
        // Récupération de la tentative, et levée d'erreur si non trouvée
        var link = UUID.fromString(uuid);
        var passwordChange = tentativeSupressionMdpRepository.findByLinkAndActiveUntilAfter(link, LocalDateTime.now())
                .orElseThrow(EntityNotFoundException::new);

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
