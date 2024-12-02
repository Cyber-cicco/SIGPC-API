package fr.diginamic.services;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import fr.diginamic.dto.TacheDto;
import fr.diginamic.dto.TacheTransformer;
import fr.diginamic.repository.TacheRepository;

import java.util.List;
@Service
@RequiredArgsConstructor
public class TacheService {

    private final TacheRepository tacheRepository;
    private final TacheTransformer tacheTransformer;

}
