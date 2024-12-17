package fr.diginamic.services;

import jakarta.persistence.EntityNotFoundException;
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

    public Tache creerTache(TacheDto tacheDto) {
        Tache tache = tacheTransformer.totache(tacheDto);
        return tacheRepository.save(tache);
    }

    public Tache modifierTache(TacheDto dto, Long id) {
        var tache = tacheRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        tache.setNom(dto.getNom());
        tache.setDescription(dto.getDescription());
        tache.setDateDebut(dto.getDateDebut());
        tache.setDateFin(dto.getDateFin());
        tache.setFinEstime(dto.getFinEstime());
        tache.setDone(dto.isDone());
        return tacheRepository.save(tache);
    }
}
