package fr.diginamic.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import fr.diginamic.dto.EquipeDto;
import org.springframework.http.ResponseEntity;
import fr.diginamic.services.EquipeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/equipe")
public class EquipeController {

    private final EquipeService equipeService;

    /**
     * Ajoute une équipe
     * 
     * Information à mettre dans le body :
     * - nom
     * - contact (optionnel)
     * - description (optionnel)
     * 
     * @param equipeDto
     * @return
     */
    @PostMapping("/ajouter")
    public ResponseEntity<EquipeDto> ajouterEquipe(@RequestBody EquipeDto equipeDto) {
        if (equipeDto.getNom() == null) {
            throw new IllegalArgumentException("Le nom est obligatoire");
        }
        EquipeDto nouvelleEquipe = equipeService.ajouterEquipe(equipeDto);
        return ResponseEntity.ok(nouvelleEquipe);
    }

}
