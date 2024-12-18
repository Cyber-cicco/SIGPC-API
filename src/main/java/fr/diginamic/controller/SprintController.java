package fr.diginamic.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import fr.diginamic.dto.SprintDto;

import org.springframework.http.ResponseEntity;
import fr.diginamic.services.SprintService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sprint")
public class SprintController {

    private final SprintService sprintService;

    /**
     * Créer un sprint
     * 
     * Informations à mettre dans le body :
     * - dateDebut
     * - dateFin
     * - userStories
     * - sprintParJour (optionnel)
     * - projetId (optionnel)
     * 
     * @param sprintDto
     * @return
     */
    @PostMapping("/creer")
    public ResponseEntity<SprintDto> creerSprint(@RequestBody SprintDto sprintDto) {
        SprintDto sprint = sprintService.creerSprint(sprintDto);
        return ResponseEntity.ok(sprint);
    }

}
