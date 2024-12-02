package fr.diginamic.services;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import fr.diginamic.dto.UtilisateurDto;
import fr.diginamic.dto.UtilisateurTransformer;
import fr.diginamic.repository.UtilisateurRepository;

import java.util.List;
@Service
@RequiredArgsConstructor
public class UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;
    private final UtilisateurTransformer utilisateurTransformer;

}
