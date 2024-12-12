package fr.diginamic.controller;

import fr.diginamic.dto.CompteDto;
import fr.diginamic.services.UtilisateurService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UtilisateurService utilisateurService;
    @PostMapping("/compte")
    public ResponseEntity<CompteDto> creerCompte(@RequestBody CompteDto compteDto) {
        utilisateurService.createAccount(compteDto);
        return ResponseEntity.ok(compteDto);
    }
}
