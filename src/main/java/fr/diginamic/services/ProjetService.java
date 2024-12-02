package fr.diginamic.services;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import fr.diginamic.dto.ProjetDto;
import fr.diginamic.dto.ProjetTransformer;
import fr.diginamic.repository.ProjetRepository;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ProjetService {

    private final ProjetRepository projetRepository;
    private final ProjetTransformer projetTransformer;

}
