package fr.diginamic.tache;

import fr.diginamic.tache.TacheDto;
import org.springframework.stereotype.Component;
import fr.diginamic.entities.Tache;
import fr.diginamic.userstory.UserStoryRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TacheTransformer {

    private final UserStoryRepository userStoryRepository;

    public TacheDto totacheDto(Tache entity) {
        TacheDto dto = new TacheDto();
        dto.setId(entity.getId());
        dto.setNom(entity.getNom());
        dto.setDescription(entity.getDescription());
        dto.setDone(entity.isDone());
        dto.setDateDebut(entity.getDateDebut());
        dto.setDateFin(entity.getDateFin());
        dto.setFinEstime(entity.getFinEstime());
        return dto;
    }

    public Tache totache(TacheDto dto) {
        Tache entity = new Tache();
        entity.setId(dto.getId());
        entity.setNom(dto.getNom());
        entity.setDescription(dto.getDescription());
        entity.setDone(dto.isDone());
        entity.setDateDebut(dto.getDateDebut());
        entity.setDateFin(dto.getDateFin());
        entity.setFinEstime(dto.getFinEstime());
        entity.setUserStory(userStoryRepository.getReferenceById(dto.getUserStoryId()));

        return entity;
    }
}
