package fr.diginamic.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import fr.diginamic.config.JwtService;
import fr.diginamic.dto.CompteDto;
import fr.diginamic.dto.LoginDto;
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
                    .body(utilisateurTransformer.toutilisateurDto(utilisateur));
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
                .body(utilisateurTransformer.toutilisateurDto(utilisateur));
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
