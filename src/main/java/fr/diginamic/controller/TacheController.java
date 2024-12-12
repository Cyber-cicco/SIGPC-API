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
        tacheService.creerTache(tacheDto);
        return ResponseEntity.ok("Tâche créée avec succès");
    }

}
