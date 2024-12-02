package fr.diginamic.services;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import fr.diginamic.dto.ProjetUtilisateurDto;
import fr.diginamic.dto.ProjetUtilisateurTransformer;
import fr.diginamic.repository.ProjetUtilisateurRepository;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ProjetUtilisateurService {

    private final ProjetUtilisateurRepository projetUtilisateurRepository;
    private final ProjetUtilisateurTransformer projetUtilisateurTransformer;

}
