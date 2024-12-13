package fr.diginamic.services;
import fr.diginamic.dto.EquipeTransformer;
import fr.diginamic.dto.SimpleInvitationDto;
import fr.diginamic.entities.Equipe;
import fr.diginamic.entities.EquipeUtilisateur;
import fr.diginamic.entities.Invitation;
import fr.diginamic.entities.enums.EquipeRoleEnum;
import fr.diginamic.entities.enums.TypeInvitationEnum;
import fr.diginamic.repository.EquipeUtilisateurRepository;
import fr.diginamic.repository.InvitationRepository;
import fr.diginamic.repository.UtilisateurRepository;
import fr.diginamic.shared.AuthenticationInfos;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import fr.diginamic.dto.EquipeDto;
import fr.diginamic.repository.EquipeRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.List;
@Service
@RequiredArgsConstructor
@Validated
public class EquipeService {

    private final EquipeRepository equipeRepository;
    private final EquipeUtilisateurRepository equipeUtilisateurRepository;
    private final InvitationRepository invitationRepository;
    private final EquipeTransformer equipeTransformer;
    private final UtilisateurRepository utilisateurRepository;

    public Equipe postNewEquipe(AuthenticationInfos userInfos, @Valid EquipeDto equipeDto) {
        var utilisateur = utilisateurRepository.findById(userInfos.getId())
                .orElseThrow(EntityNotFoundException::new);
        var equipe = equipeTransformer.toequipe(utilisateur, equipeDto);
        equipe = equipeRepository.save(equipe);
        var equipeUtilisateur = EquipeUtilisateur.builder()
                .equipe(equipe)
                .utilisateur(utilisateur)
                .role(EquipeRoleEnum.PROPRIETAIRE)
                .build();
        equipeUtilisateurRepository.save(equipeUtilisateur);
        return equipe;
    }

    @Transactional
    public void postInvite(@Valid SimpleInvitationDto invitationDto, Long groupId, AuthenticationInfos userInfos) {
        if (invitationDto.getEmail().equals(userInfos.getEmail())){
            throw new ValidationException("Vous ne pouvez pas vous inviter vous-même");
        }
        var utilisateur = utilisateurRepository.findUtilisateurByEmail(invitationDto.getEmail())
                .orElseThrow(EntityNotFoundException::new);
        var group = equipeRepository.findById(groupId)
                .orElseThrow(EntityNotFoundException::new);
        var invitation = Invitation.builder()
                .utilisateur(utilisateur)
                .equipe(group)
                .typeInvitation(TypeInvitationEnum.DEMANDE_EQUIPE)
                .acceptee(false)
                .dateEmission(LocalDateTime.now())
                .build();
        invitationRepository.save(invitation);
    }
}
