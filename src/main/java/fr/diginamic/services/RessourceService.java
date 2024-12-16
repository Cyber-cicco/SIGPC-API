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
                .orElseThrow(() -> new IllegalArgumentException("La ressource avec l'id " + id + " n'existe pas"));

        ressourceRepository.delete(ressourceToDelete);
    }

}
