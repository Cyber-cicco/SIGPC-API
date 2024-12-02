package fr.diginamic.services;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import fr.diginamic.dto.TentativeSupressionMdpDto;
import fr.diginamic.dto.TentativeSupressionMdpTransformer;
import fr.diginamic.repository.TentativeSupressionMdpRepository;

import java.util.List;
@Service
@RequiredArgsConstructor
public class TentativeSupressionMdpService {

    private final TentativeSupressionMdpRepository tentativeSupressionMdpRepository;
    private final TentativeSupressionMdpTransformer tentativeSupressionMdpTransformer;

}
