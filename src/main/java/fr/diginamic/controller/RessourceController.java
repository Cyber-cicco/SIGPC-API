package fr.diginamic.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import fr.diginamic.dto.RessourceDto;
import org.springframework.http.ResponseEntity;
import fr.diginamic.services.RessourceService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ressource")
public class RessourceController {

    private final RessourceService ressourceService;

    /**
     * Ajoute une ressource à un projet
     * 
     * Information à mettre dans le body :
     * - projetId
     * - nom (optionnel)
     * - lien (optionnel)
     * - lienMedia (optionnel)
     * 
     * @param ressourceDto
     * @return
     */
    @PostMapping("/add")
    public ResponseEntity<RessourceDto> ajouterRessource(@RequestBody RessourceDto ressourceDto) {
        if (ressourceDto.getProjet() == null) {
            throw new IllegalArgumentException("Le projet est obligatoire");
        }
        RessourceDto nouvelleRessource = ressourceService.ajouterRessource(ressourceDto);
        return ResponseEntity.ok(nouvelleRessource);
    }

}
