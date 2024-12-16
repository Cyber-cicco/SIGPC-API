package fr.diginamic.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import fr.diginamic.dto.UserStoryDto;
import fr.diginamic.dto.UserStoryTransformer;
import fr.diginamic.entities.UserStory;
import fr.diginamic.repository.UserStoryRepository;

@Service
@RequiredArgsConstructor
public class UserStoryService {

    private final UserStoryRepository userStoryRepository;
    private final UserStoryTransformer userStoryTransformer;

    public UserStoryDto createUserStory(UserStoryDto userStoryDto) {
        UserStory userStory = userStoryTransformer.touserStory(userStoryDto);
        UserStory savedUserStory = userStoryRepository.save(userStory);
        return userStoryTransformer.touserStoryDto(savedUserStory);
    }

}
