package fr.diginamic.dto;

import fr.diginamic.entities.enums.AvancementEnum;
import fr.diginamic.projet.ProjetRepository;
import fr.diginamic.repository.UserStoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import fr.diginamic.entities.UserStory;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserStoryTransformer {

    private final ProjetRepository projetRepository;
    private final UserStoryRepository userStoryRepository;
    public UserStoryDto touserStoryDto(UserStory entity) {
        UserStoryDto dto = new UserStoryDto();
        dto.setId(entity.getId());
        dto.setLibelle(entity.getLibelle());
        dto.setCode(entity.getCode());
        dto.setDescription(entity.getDescription());
        dto.setDateDebut(entity.getDateDebut());
        dto.setAvancement(entity.getAvancement());
        dto.setDateFin(entity.getDateFin());

        // TODO : implémenter les méthodes pour les champs complexes
        return dto;
    }

    public UserStory touserStory(UserStoryDto dto) {
        var projet = projetRepository.getReferenceById(dto.getProjetId());
        Optional<Long> prevId = userStoryRepository.getMaxId();
        UserStory entity = new UserStory();
        entity.setId(dto.getId());
        entity.setLibelle(dto.getLibelle());
        entity.setCode(projet.getNom() + " #" + (prevId.isPresent() ? prevId.get() + 1 : "1") );
        entity.setDescription(dto.getDescription());
        entity.setDateDebut(dto.getDateDebut());
        if (dto.getAvancement() != null) {
            entity.setAvancement(dto.getAvancement());
        } else {
            entity.setAvancement(AvancementEnum.TODO);
        }
        entity.setDateFin(dto.getDateFin());
        entity.setProjet(projet);

        // TODO : implémenter les méthodes pour les champs complexes
        return entity;
    }
}
