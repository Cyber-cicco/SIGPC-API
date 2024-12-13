package fr.diginamic.controller;

import brevo.ApiException;
import com.fasterxml.jackson.core.JsonProcessingException;
import fr.diginamic.config.JwtService;
import fr.diginamic.dto.CompteDto;
import fr.diginamic.dto.LoginDto;
import fr.diginamic.dto.PasswordChangeDto;
import fr.diginamic.dto.UtilisateurTransformer;
import fr.diginamic.services.MailService;
import fr.diginamic.services.UtilisateurService;
import fr.diginamic.shared.ErrorMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UtilisateurService utilisateurService;
    private final JwtService jwtService;
    private final MailService mailService;
    private final UtilisateurTransformer utilisateurTransformer;

    /**
     * Permet de créer un compte en envoyant des informations selon le format spécifié par la
     * classe CompteDto
     * @param compteDto les informations du nouveau compte
     * @return une réponse pouvant être les informations de l'utilisateur ou un message d'erreur
     * @throws JsonProcessingException
     */
    @PostMapping("/compte")
    public ResponseEntity<?> creerCompte(@RequestBody CompteDto compteDto) throws JsonProcessingException {
        var utilisateur = utilisateurService.createAccount(compteDto);
        try {
            var jwt = jwtService.buildJWTCookie(utilisateur);
            mailService.sendVerificationEmail(utilisateur);
            return ResponseEntity.status(200)
                    .header(HttpHeaders.SET_COOKIE, jwt)
                    .body(utilisateurTransformer.toUtilisateurDto(utilisateur));
        } catch (Exception e) {
            //Suppression de l'utilisateur dans le cas où le mail de confirmation n'a pas pu être envoyé
            utilisateurService.deleteAccount(utilisateur.getEmail());
            return ResponseEntity.status(500).body(new ErrorMessage("Erreur lors de l'envoie du mail de confirmation. Votre compte n'a pas pu être créé"));
        }
    }

    /**
     * Permet de s'authentifier. Renvoie une réponse avec un cookie contenant
     * le token d'authentification
     * @param loginDto les informations de login
     * @return la réponse
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        var utilisateur = utilisateurService.login(loginDto);
        var jwt = jwtService.buildJWTCookie(utilisateur);
        return ResponseEntity.status(200)
                .header(HttpHeaders.SET_COOKIE, jwt)
                .body(utilisateurTransformer.toUtilisateurDto(utilisateur));
    }

    /**
     * Permet d'envoyer un email de récupération de mot de passe à l'email précisé
     * @param loginDto l'objet contenant l'email
     * @return une réponse contenant le lien d'activation en tant que chaine de caractère
     * @throws ApiException
     */
    @PostMapping("/password-change/send-request")
    public ResponseEntity<?> createNewPasswordChangeAttempt(@RequestBody LoginDto loginDto) {
        var askMdp = utilisateurService.createNewPasswordChangeAttempt(loginDto);
        try {
            mailService.sendPasswordChangeEmail(askMdp.tentativeChangementMdp(), askMdp.email());
            return ResponseEntity.ok(Map.of("message", askMdp.tentativeChangementMdp().getLink().toString()));
        } catch (ApiException e) {
            utilisateurService.removePasswordChangeAttempt(askMdp);
            return ResponseEntity.ok(Map.of("message", "Erreur lors de l'envoie du mail"));
        }
    }

    /**
     * Permet de changer le mot de passe
     * @param uuid l'identifiant unique de la tentative de changement de mot de passe
     * @param passwordChangeDto object contenant le mot de passe et sa confirmation
     * @return une réponse indiquant la réussite de la transaction
     * @throws ApiException
     */
    @PostMapping("/password/change/{uuid}")
    public ResponseEntity<?> changePassword(@PathVariable("uuid") String uuid, @RequestBody PasswordChangeDto passwordChangeDto) {
        utilisateurService.changePassword(uuid, passwordChangeDto);
        return ResponseEntity.ok(Map.of("message", "success"));
    }

    /**
     * Permet de vérifier le mail d'un utilisateur
     * @param uuid identifiant du lien
     * @return une réponse indiquant la réussite de l'opération
     */
    @GetMapping("/email/verify/{uuid}")
    public ResponseEntity<?> verfiyEmail(@PathVariable("uuid") String uuid) {
        utilisateurService.activateAccount(uuid);
        return ResponseEntity.ok(Map.of("message", "activation réussie"));
    }
}
