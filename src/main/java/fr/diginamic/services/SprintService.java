package fr.diginamic.services;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import fr.diginamic.dto.SprintDto;
import fr.diginamic.dto.SprintTransformer;
import fr.diginamic.repository.SprintRepository;

import java.util.List;
@Service
@RequiredArgsConstructor
public class SprintService {

    private final SprintRepository sprintRepository;
    private final SprintTransformer sprintTransformer;

}
