package fr.diginamic.services;

import fr.diginamic.projet.Projet;
import fr.diginamic.projet.ProjetRepository;
import fr.diginamic.repository.TacheRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import fr.diginamic.dto.UserStoryDto;
import fr.diginamic.dto.UserStoryTransformer;
import fr.diginamic.entities.UserStory;
import fr.diginamic.repository.UserStoryRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserStoryService {

    private final UserStoryRepository userStoryRepository;
    private final UserStoryTransformer userStoryTransformer;
    private final ProjetRepository projetRepository;
    private final TacheRepository tacheRepository;

    public UserStoryDto createUserStory(UserStoryDto userStoryDto) {
        UserStory userStory = userStoryTransformer.touserStory(userStoryDto);
        UserStory savedUserStory = userStoryRepository.save(userStory);
        return userStoryTransformer.touserStoryDto(savedUserStory);
    }

    public UserStory findUsById(Long idUserStory) {
        var userStory = userStoryRepository.findById(idUserStory)
                .orElseThrow(EntityNotFoundException::new);
        return userStory;
    }

    public void remove(UserStory userStory) {
        userStory.setDeletedAt(LocalDateTime.now());
        userStoryRepository.save(userStory);
    }

    public UserStory change(UserStory userStory, UserStoryDto userStoryDto) {
        userStory.setLibelle(userStoryDto.getLibelle());
        userStory.setDescription(userStoryDto.getDescription());
        userStory.setAvancement(userStoryDto.getAvancement());
        userStory.setDateDebut(userStoryDto.getDateDebut());
        userStory.setFinEstime(userStoryDto.getFinEstime());
        userStory.setDateFin(userStoryDto.getDateFin());
        return userStoryRepository.save(userStory);
    }
}
