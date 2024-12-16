package fr.diginamic.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import fr.diginamic.dto.RessourceTransformer;
import fr.diginamic.entities.Ressource;
import fr.diginamic.repository.RessourceRepository;
import jakarta.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class RessourceService {

    private final RessourceRepository ressourceRepository;
    private final RessourceTransformer ressourceTransformer;

    @Transactional
    public void supprimerRessource(Long id) {
        Ressource ressourceToDelete = ressourceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Le fichier n'existe pas (id : " + id + ")"));

        ressourceRepository.delete(ressourceToDelete);
    }

}
