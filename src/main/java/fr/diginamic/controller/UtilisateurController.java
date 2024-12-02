package fr.diginamic.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;    
import fr.diginamic.dto.UtilisateurDto;
import org.springframework.http.ResponseEntity;
import fr.diginamic.services.UtilisateurService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/utilisateur")
public class UtilisateurController {

   
    private final UtilisateurService utilisateurService;

}
