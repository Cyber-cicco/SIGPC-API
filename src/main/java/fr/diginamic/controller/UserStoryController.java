package fr.diginamic.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import fr.diginamic.dto.UserStoryDto;
import org.springframework.http.ResponseEntity;
import fr.diginamic.services.UserStoryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/userStory")
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
    public ResponseEntity<UserStoryDto> createUserStory(@RequestBody UserStoryDto userStoryDto) {
        if (userStoryDto.getLibelle() == null) {
            throw new IllegalArgumentException("Le libellé est obligatoire");
        }
        if (userStoryDto.getCode() == null) {
            throw new IllegalArgumentException("Le code est obligatoire");
        }
        if (userStoryDto.getProjet() == null) {
            throw new IllegalArgumentException("Le projet est obligatoire");
        }
        UserStoryDto createdUserStory = userStoryService.createUserStory(userStoryDto);
        return ResponseEntity.ok(createdUserStory);
    }
}
