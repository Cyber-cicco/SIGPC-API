package fr.diginamic.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import fr.diginamic.dto.TacheDto;
import org.springframework.http.ResponseEntity;
import fr.diginamic.services.TacheService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tache")
public class TacheController {

    private final TacheService tacheService;

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
    @PostMapping("/create")
    public ResponseEntity<String> creerTache(@RequestBody TacheDto tacheDto) {
        // Vérification du nom de la tâche
        if (tacheDto.getNom() == null || tacheDto.getNom().isEmpty()) {
            throw new IllegalArgumentException("Le nom de la tâche est obligatoire");
        }
        if (tacheDto.getNom().length() > 60) {
            throw new IllegalArgumentException("Le nom de la tâche est trop long");
        }
        // Vérification du userStoryId
        if (tacheDto.getUserStoryId() == null) {
            throw new IllegalArgumentException("L'ID de l'user story est obligatoire");
        }
        // Création de la tâche
        tacheService.creerTache(tacheDto);
        return ResponseEntity.ok("Tâche créée avec succès");
    }

}
