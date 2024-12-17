package fr.diginamic.controller;

import fr.diginamic.services.SecurityService;
import fr.diginamic.shared.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import fr.diginamic.dto.UserStoryDto;
import org.springframework.http.ResponseEntity;
import fr.diginamic.services.UserStoryService;
import org.springframework.web.bind.annotation.*;

import static fr.diginamic.config.Constants.API_VERSION_1;
import static fr.diginamic.config.Constants.AUTH_TOKEN;
import static fr.diginamic.shared.ResponseUtil.success;

@RestController
@RequiredArgsConstructor
@RequestMapping(API_VERSION_1 + "/user-stories")
public class UserStoryController {

    private final UserStoryService userStoryService;
    private final SecurityService securityService;

    /**
     * Créer une UserStory
     * 
     * Informations à mettre dans le body :
     * - libelle
     * - code
     * - projet id 
     * - description (optionnel)
     * - dateDebut (optionnel)
     * - avancement (optionnel)
     * - dateFin (optionnel)
     * 
     * @param userStoryDto
     * @return
     */
    @PostMapping
    public ResponseEntity<ApiResponse<UserStoryDto>> createUserStory(@CookieValue(AUTH_TOKEN) String token, @Valid @RequestBody UserStoryDto userStoryDto) {
        if (userStoryDto.getLibelle() == null) {
            throw new IllegalArgumentException("Le libellé est obligatoire");
        }
        if (userStoryDto.getProjetId() == null) {
            throw new IllegalArgumentException("Le projet est obligatoire");
        }
        UserStoryDto createdUserStory = userStoryService.createUserStory(userStoryDto);
        return ResponseEntity.ok(success("user story créée", createdUserStory));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> deleteUserStory(@CookieValue(AUTH_TOKEN) String token, @PathVariable("id") Long idUserStory) {
        var userInfos = securityService.getAuthenticationInfos(token);
        var userStory = userStoryService.findUsById(idUserStory);
        var projetUtilisateur = securityService.checkIfUserMemberOfProject(userInfos.getId(), userStory.getProjet().getId());
        securityService.shouldNotBeVisiteurOfProject(projetUtilisateur);
        userStoryService.remove(userStory);
        return ResponseEntity.ok(success("user story supprimée"));
    }

}
