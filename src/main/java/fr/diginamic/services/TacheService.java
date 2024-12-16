package fr.diginamic.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import fr.diginamic.dto.TacheDto;
import fr.diginamic.dto.TacheTransformer;
import fr.diginamic.entities.Tache;
import fr.diginamic.repository.TacheRepository;

@Service
@RequiredArgsConstructor
public class TacheService {

    private final TacheRepository tacheRepository;
    private final TacheTransformer tacheTransformer;

    public void creerTache(TacheDto tacheDto) {
        Tache tache = tacheTransformer.totache(tacheDto);
        tacheRepository.save(tache);
    }

}
