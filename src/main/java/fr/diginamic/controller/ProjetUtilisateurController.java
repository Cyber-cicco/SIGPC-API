package fr.diginamic.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;    
import fr.diginamic.dto.ProjetUtilisateurDto;
import org.springframework.http.ResponseEntity;
import fr.diginamic.services.ProjetUtilisateurService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/projetUtilisateur")
public class ProjetUtilisateurController {

   
    private final ProjetUtilisateurService projetUtilisateurService;

}
