package fr.diginamic.controller;

import fr.diginamic.dto.EquipeDto;
import fr.diginamic.dto.EquipeTransformer;
import fr.diginamic.entities.Equipe;
import fr.diginamic.services.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import fr.diginamic.services.EquipeService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/equipes")
public class EquipeController {


    private final EquipeService equipeService;
    private final EquipeTransformer equipeTransformer;
    private final SecurityService securityService;

    @PostMapping
    public ResponseEntity<?> postNewEquipe(@CookieValue("AUTH-TOKEN") String token, @RequestBody EquipeDto equipeDto) {
        var userInfos = securityService.getAuthenticationInfos(token);
        Equipe equipe = equipeService.postNewEquipe(userInfos, equipeDto);
        return ResponseEntity.ok(equipeTransformer.toequipeDto(equipe));
    }

}
