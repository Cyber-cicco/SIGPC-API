package fr.diginamic.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import fr.diginamic.dto.RessourceDto;
import org.springframework.http.ResponseEntity;
import fr.diginamic.services.RessourceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ressource")
public class RessourceController {

    private final RessourceService ressourceService;

    /**
     * Supprime une ressource avec l'id passé en paramètre.
     * 
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRessource(@PathVariable Long id) {
        ressourceService.supprimerRessource(id);
        return ResponseEntity.noContent().build();
    }

}
