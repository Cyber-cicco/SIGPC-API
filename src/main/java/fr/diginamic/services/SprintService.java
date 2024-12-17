package fr.diginamic.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import fr.diginamic.dto.SprintDto;
import fr.diginamic.dto.SprintTransformer;
import fr.diginamic.entities.Sprint;
import fr.diginamic.repository.SprintRepository;

@Service
@RequiredArgsConstructor
public class SprintService {

    private final SprintRepository sprintRepository;
    private final SprintTransformer sprintTransformer;

    public SprintDto creerSprint(SprintDto sprintDto) {
        Sprint sprint = sprintTransformer.tosprint(sprintDto);
        Sprint savedSprint = sprintRepository.save(sprint);
        return sprintTransformer.tosprintDto(savedSprint);
    }

}
