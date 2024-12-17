package fr.diginamic.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import fr.diginamic.dto.RessourceDto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import fr.diginamic.services.RessourceService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ressource")
public class RessourceController {

    private final RessourceService ressourceService;

    @PostMapping("/ajout")
    public ResponseEntity<RessourceDto> ajoutRessource(@RequestBody RessourceDto ressourceDto) {
        if (ressourceDto.getProjetId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Le projet est obligatoire");
        }
        RessourceDto createdRessource = ressourceService.ajoutRessource(ressourceDto);
        return ResponseEntity.ok(createdRessource);
    }
}
