package fr.diginamic.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import fr.diginamic.dto.RessourceDto;
import fr.diginamic.dto.RessourceTransformer;
import fr.diginamic.entities.Ressource;
import fr.diginamic.repository.RessourceRepository;

@Service
@RequiredArgsConstructor
public class RessourceService {

    private final RessourceRepository ressourceRepository;
    private final RessourceTransformer ressourceTransformer;

    public RessourceDto ajouterRessource(RessourceDto ressourceDto) {
        Ressource ressource = ressourceTransformer.toressource(ressourceDto);
        ressource = ressourceRepository.save(ressource);
        return ressourceTransformer.toressourceDto(ressource);
    }

}
