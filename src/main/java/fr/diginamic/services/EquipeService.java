package fr.diginamic.services;
import fr.diginamic.dto.EquipeTransformer;
import fr.diginamic.dto.RoleEquipeDto;
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

@Service
@RequiredArgsConstructor
@Validated
public class EquipeService {

    private final EquipeRepository equipeRepository;
    private final EquipeUtilisateurRepository equipeUtilisateurRepository;
    private final InvitationRepository invitationRepository;
    private final EquipeTransformer equipeTransformer;
    private final UtilisateurRepository utilisateurRepository;

    /**
     * Permet d'ajouter une équipe
     * @param userInfos les informations de l'utilisateur connecté
     * @param equipeDto les informations de la nouvelle équipe (validée)
     * @return la nouvelle équipe
     */
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
        var group = equipeRepository.getReferenceById(groupId);
        var invitation = Invitation.builder()
                .utilisateur(utilisateur)
                .equipe(group)
                .typeInvitation(TypeInvitationEnum.DEMANDE_EQUIPE)
                .acceptee(false) // TODO : changer pour un enum
                .dateEmission(LocalDateTime.now())
                .build();
        invitationRepository.save(invitation);
    }

    /**
     * Permet de supprimer une invitation lorsqu'une erreur d'envoie de mail se produit
     * @param invitationDto les informations de l'invitation
     * @param groupId l'identifiant du groupe de l'invitation
     */
    public void removeInvite(SimpleInvitationDto invitationDto, Long groupId) {
        invitationRepository.deleteByUtilisateur_EmailAndEquipe_Id(invitationDto.getEmail(), groupId);
    }

    /**
     * Permet d'accepter ou de refuser une invitation
     * @param userInfos les informations de l'utilisateur connecté
     * @param groupId l'identifiant du groupe qu'il souhaite rejoindre
     * @param accepted indique s'il accepte ou refuse l'invitation
     */
    @Transactional
    public void accepteInvite(AuthenticationInfos userInfos, Long groupId, Boolean accepted) {
        var invitation = invitationRepository.findFirstByUtilisateur_IdAndEquipe_IdAndTypeInvitationOrderByDateEmissionDesc(userInfos.getId(), groupId, TypeInvitationEnum.DEMANDE_EQUIPE)
                .orElseThrow(EntityNotFoundException::new);
        if (LocalDateTime.now().isAfter(invitation.getDateEmission().plusDays(7))) {
            throw new ValidationException("L'invitation a expiré");
        }
        invitation.setAcceptee(accepted); // TODO : changer par un enum
        invitation.setDateAcceptation(LocalDateTime.now());
        invitationRepository.save(invitation);
        if (accepted) {
            var utilisateur = utilisateurRepository.getReferenceById(userInfos.getId());
            var equipe = equipeRepository.getReferenceById(groupId);
            var equipeUtilisateur = EquipeUtilisateur.builder()
                    .utilisateur(utilisateur)
                    .equipe(equipe)
                    .role(EquipeRoleEnum.MEMBRE)
                    .build();
            equipeUtilisateurRepository.save(equipeUtilisateur);
        }
    }

    /**
     * Permet de changer le rôle d'un utilisateur non propriétaire
     * @param memberId membre dont on veut changer le rôle
     * @param groupId équipe auquelle le membre appartient
     * @param roleEquipeDto rôle que l'on souhaite assigner au membre
     */
    public EquipeUtilisateur changeRole(Long memberId, Long groupId, RoleEquipeDto roleEquipeDto) {
        var equipeUtilisateur = equipeUtilisateurRepository.findByUtilisateur_IdAndEquipe_Id(memberId, groupId)
                .orElseThrow(EntityNotFoundException::new);
        if (equipeUtilisateur.getRole().equals(EquipeRoleEnum.PROPRIETAIRE)) {
            throw new ValidationException("Vous ne pouvez changer le rôle d'un propriétaire");
        }
        if (equipeUtilisateur.getRole().equals(roleEquipeDto.getRole())) {
            throw new ValidationException("L'utilisateur possède déjà ce rôle");
        }
        equipeUtilisateur.setRole(roleEquipeDto.getRole());
        return equipeUtilisateurRepository.save(equipeUtilisateur);
    }

    public void leaveGroup(AuthenticationInfos userInfos, Long groupId) {
        var group = equipeUtilisateurRepository.findByUtilisateur_IdAndEquipe_Id(userInfos.getId(), groupId)
                .orElseThrow(EntityNotFoundException::new);
        equipeUtilisateurRepository.delete(group);
    }

    public record DemandeAjoutEquipe(String senderEmail, String recipientEmail){}

    /**
     * Permet d'ajouter une demande d'appartenance à un groupe
     * @param userInfos les informations de l'utilisateur connecté
     * @param groupId le groupe qu'il souhaite rejoindre
     * @return l'email de l'administrateur
     */
    public DemandeAjoutEquipe addJoinGroupDemand(AuthenticationInfos userInfos, Long groupId) {
        var utilisateur = utilisateurRepository.getReferenceById(userInfos.getId());
        var group = equipeRepository.findById(groupId)
                .orElseThrow(EntityNotFoundException::new);
        var invitation = Invitation.builder()
                .dateEmission(LocalDateTime.now())
                .acceptee(false)
                .equipe(group)
                .utilisateur(utilisateur)
                .typeInvitation(TypeInvitationEnum.DEMANDE_UTILISATEUR)
                .build();
        invitationRepository.save(invitation);
        return new DemandeAjoutEquipe(utilisateur.getEmail(), group.getAdmin().getEmail());
    }
}
