package fr.diginamic.controller;

import static fr.diginamic.config.Constants.API_VERSION_1;
import static fr.diginamic.config.Constants.AUTH_TOKEN;
import static fr.diginamic.shared.ResponseUtil.success;

import fr.diginamic.dto.TacheTransformer;
import fr.diginamic.services.SecurityService;
import fr.diginamic.shared.ApiResponse;
import fr.diginamic.shared.AuthenticationInfos;
import fr.diginamic.userstory.UserStoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import fr.diginamic.dto.TacheDto;
import org.springframework.http.ResponseEntity;
import fr.diginamic.services.TacheService;

@RestController
@RequiredArgsConstructor
@RequestMapping(API_VERSION_1 + "/taches")
public class TacheController {

    private final TacheService tacheService;
    private final TacheTransformer tacheTransformer;
    private final UserStoryService userStoryService;
    private final SecurityService securityService;

    /**
     * Crée une tâche
     * 
     * Informations à mettre dans le body :
     * - nom
     * - description
     * - userStoryId
     * 
     * @param tacheDto
     * @return
     */
    @PostMapping
    public ResponseEntity<ApiResponse<TacheDto>> creerTache(
            @CookieValue(AUTH_TOKEN) String token,
            @RequestBody @Valid TacheDto tacheDto
    ) {
        checkIfUserIsAllowedToChangeTask(token, tacheDto);
        var tache  = tacheService.creerTache(tacheDto);
        return ResponseEntity.ok(success("Tâche créée avec succès", tacheTransformer.totacheDto(tache)));
    }

    private AuthenticationInfos checkIfUserIsAllowedToChangeTask(String token, TacheDto tacheDto){
        var userInfos = securityService.getAuthenticationInfos(token);
        var userStory = userStoryService.findUsById(tacheDto.getUserStoryId());
        var projetUtilisateur = securityService.checkIfUserMemberOfProject(userInfos.getId(), userStory.getProjet().getId());
        securityService.shouldNotBeVisiteurOfProject(projetUtilisateur);
        return userInfos;
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<TacheDto>> modifierTache(
            @CookieValue(AUTH_TOKEN) String token,
            @RequestBody @Valid TacheDto tacheDto,
            @PathVariable Long id
    ) {
        checkIfUserIsAllowedToChangeTask(token, tacheDto);
        var tache = tacheService.modifierTache(tacheDto, id);
        return ResponseEntity.ok(success("Tâche modifiée avec succès", tacheTransformer.totacheDto(tache)));
    }




}
