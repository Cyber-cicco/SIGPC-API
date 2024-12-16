package fr.diginamic.utilisateur;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/utilisateur")
public class UtilisateurController {

   
    private final UtilisateurService utilisateurService;

}
