package fr.diginamic.services;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import fr.diginamic.dto.UserStoryDto;
import fr.diginamic.dto.UserStoryTransformer;
import fr.diginamic.repository.UserStoryRepository;

import java.util.List;
@Service
@RequiredArgsConstructor
public class UserStoryService {

    private final UserStoryRepository userStoryRepository;
    private final UserStoryTransformer userStoryTransformer;

}
