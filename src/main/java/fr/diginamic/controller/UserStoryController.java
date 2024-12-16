package fr.diginamic.controller;

import fr.diginamic.shared.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import fr.diginamic.dto.UserStoryDto;
import org.springframework.http.ResponseEntity;
import fr.diginamic.services.UserStoryService;
import org.springframework.web.bind.annotation.*;

import static fr.diginamic.config.Constants.API_VERSION_1;
import static fr.diginamic.shared.ResponseUtil.success;

@RestController
@RequiredArgsConstructor
@RequestMapping(API_VERSION_1 + "/user-stories")
public class UserStoryController {

    private final UserStoryService userStoryService;

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
    public ResponseEntity<ApiResponse<UserStoryDto>> createUserStory(@RequestBody UserStoryDto userStoryDto) {
        if (userStoryDto.getLibelle() == null) {
            throw new IllegalArgumentException("Le libellé est obligatoire");
        }
        if (userStoryDto.getProjetId() == null) {
            throw new IllegalArgumentException("Le projet est obligatoire");
        }
        UserStoryDto createdUserStory = userStoryService.createUserStory(userStoryDto);
        return ResponseEntity.ok(success("user story créée", createdUserStory));
    }
}
