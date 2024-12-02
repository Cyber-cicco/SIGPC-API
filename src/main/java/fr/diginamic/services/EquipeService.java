package fr.diginamic.services;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import fr.diginamic.dto.EquipeDto;
import fr.diginamic.dto.EquipeTransformer;
import fr.diginamic.repository.EquipeRepository;

import java.util.List;
@Service
@RequiredArgsConstructor
public class EquipeService {

    private final EquipeRepository equipeRepository;
    private final EquipeTransformer equipeTransformer;

}
