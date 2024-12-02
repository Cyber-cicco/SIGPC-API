package fr.diginamic.services;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import fr.diginamic.dto.RessourceDto;
import fr.diginamic.dto.RessourceTransformer;
import fr.diginamic.repository.RessourceRepository;

import java.util.List;
@Service
@RequiredArgsConstructor
public class RessourceService {

    private final RessourceRepository ressourceRepository;
    private final RessourceTransformer ressourceTransformer;

}
