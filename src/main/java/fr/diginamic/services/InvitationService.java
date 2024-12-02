package fr.diginamic.services;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import fr.diginamic.dto.InvitationDto;
import fr.diginamic.dto.InvitationTransformer;
import fr.diginamic.repository.InvitationRepository;

import java.util.List;
@Service
@RequiredArgsConstructor
public class InvitationService {

    private final InvitationRepository invitationRepository;
    private final InvitationTransformer invitationTransformer;

}
