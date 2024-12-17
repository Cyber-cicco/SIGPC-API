package fr.diginamic.controller;

import brevo.ApiException;
import fr.diginamic.dto.EquipeDto;
import fr.diginamic.dto.EquipeTransformer;
import fr.diginamic.dto.RoleEquipeDto;
import fr.diginamic.dto.SimpleInvitationDto;
import fr.diginamic.entities.Equipe;
import fr.diginamic.services.MailService;
import fr.diginamic.services.SecurityService;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import fr.diginamic.services.EquipeService;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/equipes")
public class EquipeController {


    private final EquipeService equipeService;
    private final EquipeTransformer equipeTransformer;
    private final MailService mailService;
    private final SecurityService securityService;

    /**
     * Permet d'ajouter une nouvelle équipe
     * @param token le jwt
     * @param equipeDto les informations de l'équipe
     * @return les informations de la nouvelle équipe
     */
    @PostMapping
    public ResponseEntity<EquipeDto> postNewEquipe(@CookieValue("AUTH-TOKEN") String token, @RequestBody EquipeDto equipeDto) {
        var userInfos = securityService.getAuthenticationInfos(token);
        Equipe equipe = equipeService.postNewEquipe(userInfos, equipeDto);
        return ResponseEntity.ok(equipeTransformer.toequipeDto(equipe));
    }

    /**
     * Permet d'envoyer une invitation à un utilisateur pour rejoindre une équipe
     * à laquelle on appartient
     * @param token le jwt
     * @param groupId l'identifiant de l'équipe
     * @param invitationDto contient le mail de la personne à qui l'on fait l'invitation
     * @return un message indiquant la réussite de l'opération
     */
    @PostMapping("/{groupId}/invite")
    public ResponseEntity<?> postInvitation(@CookieValue("AUTH-TOKEN") String token, @PathVariable("groupId") Long groupId, @RequestBody SimpleInvitationDto invitationDto) {
        var userInfos = securityService.getAuthenticationInfos(token);
        securityService.checkIfUserAllowedInGroup(userInfos.getId(), groupId);
        equipeService.postInvite(invitationDto, groupId, userInfos);
        try {
            mailService.sendInvitation(invitationDto);
            return ResponseEntity.ok(Map.of("message", "invite sent"));
        } catch (ApiException e) {
            equipeService.removeInvite(invitationDto, groupId);
            return ResponseEntity.ok(Map.of("message", "L'invitation n'a pas aboutis à cause d'un problème lié à l'envoie de mail."));
        }
    }

    /**
     * Permet d'accepter une invitation
     * @param token le jwt
     * @param groupId l'identifiant du groupe
     * @param accepted indique si l'invitation a été acceptée ou refusée
     * @return un message en fonction du status d'acceptation de l'invitation
     */
    @PatchMapping("/{groupId}/invite/member/{accepted}")
    public ResponseEntity<?> accepteInvitation(
            @CookieValue("AUTH-TOKEN") String token,
            @PathVariable Long groupId,
            @PathVariable Boolean accepted
            ) {
        var userInfos = securityService.getAuthenticationInfos(token);
        equipeService.accepteInvite(userInfos, groupId, accepted);
        return ResponseEntity.ok(Map.of("message", accepted ?
                "Vous avez bien été ajouté dans le groupe"
                :
                "L'invitation a correctement été refusée"));
    }

    /**
     * Permet de changer le rôle d'un utilisateur dans une équipe dont
     * on est le propriétaire
     * @param token jwt
     * @param groupId identifiant du groupe visé
     * @param memberId identifiant de l'utilisateur visé
     * @param roleEquipeDto rôle que l'on souhaite assigner
     * @return le rôle
     */
    @PatchMapping("/{groupId}/member/{memberId}/role")
    public ResponseEntity<?> changeRoleOfMember(@CookieValue("AUTH-TOKEN") String token, @PathVariable Long groupId, @PathVariable Long memberId, @RequestBody RoleEquipeDto roleEquipeDto) {
        var userInfos = securityService.getAuthenticationInfos(token);
        securityService.checkIfUserProprietaireInGroup(userInfos.getId(), groupId);
        var utilisateurEquipe = equipeService.changeRole(memberId, groupId, roleEquipeDto);
        return ResponseEntity.ok(new RoleEquipeDto(utilisateurEquipe.getRole()));
    }

    /**
     * Envoie une demande d'appartenance à un groupe
     * @param token le jwt
     * @param groupId le groupe auquel l'utilisateur souhaite appartenir
     * @return un message indiquant que la demande a été faite
     * @throws ApiException si le mail n'a pas pu être envoyé
     */
    @PostMapping("/{groupId}/join")
    public ResponseEntity<?> askToJoinGroup(@CookieValue("AUTH-TOKEN") String token, @PathVariable Long groupId) throws ApiException {
        var userInfos = securityService.getAuthenticationInfos(token);
        var emails = equipeService.addJoinGroupDemand(userInfos, groupId);
        mailService.sendDemandeAppartenance(emails.senderEmail(), emails.recipientEmail());
        return ResponseEntity.ok(Map.of("message", "La demande a été envoyée"));
    }

    /**
     * Permet de partir d'un groupe
     * @param token le jwt
     * @param groupId le groupe que l'on souhaite quitter
     * @return une réponse indiquant que l'on a bien quitté le groupe
     */
    @PatchMapping("/{groupId}/leave")
    public ResponseEntity<?> leaveGroup(@CookieValue("AUTH-TOKEN") String token, @PathVariable Long groupId) {
        var userInfos = securityService.getAuthenticationInfos(token);
        securityService.checkIfUserAllowedInGroup(userInfos.getId(), groupId);
        equipeService.leaveGroup(userInfos.getId(), groupId);
        return ResponseEntity.ok(Map.of("message", "Vous avez bien quitter le groupe"));
    }

    /**
     * Permet de supprimer un utilisateur d'un groupe
     * @param token le jwt
     * @param memberId le membre à supprimer
     * @param groupId l'identifiant du groupe auquel vous appartenez
     * @return une réponse indiquant la suppression de l'utilisateur
     */
    @DeleteMapping("/{groupId}/member/{memberId}")
    public ResponseEntity<?> removeFromGroup(@CookieValue("AUTH-TOKEN") String token, @PathVariable Long memberId, @PathVariable Long groupId) {
        var userInfos = securityService.getAuthenticationInfos(token);
        securityService.checkIfUserProprietaireInGroup(userInfos.getId(), groupId);
        securityService.checkIfUserAllowedInGroup(memberId, groupId);
        if (userInfos.getId().equals(memberId)) {
            throw new ValidationException("Vous ne pouvez vous supprimer vous-même. Utiliser l'option pour partir du groupe");
        }
        equipeService.leaveGroup(memberId, groupId);
        return ResponseEntity.ok(Map.of("message", "Vous avez bien supprimé l'utilisateur du groupe"));
    }

    /**
     * Permet d'accepter la demande pour rejoindre un groupe
     * @param token le jwt
     * @param groupId l'identifiant du groupe à rejoindre
     * @param memberId l'identifiant de l'utilisateur que l'on accepte
     * @param accepted si l'on accepte ou non
     * @return un message retournant le status d'acceptation de la demande
     */
    @PatchMapping("/{groupId}/join-request/member/{memberId}/{accepted}")
    public ResponseEntity<?> acceptJoinDemand(@CookieValue("AUTH-TOKEN") String token, @PathVariable Long groupId, @PathVariable Long memberId, @PathVariable Boolean accepted) throws ApiException {
        var userInfos = securityService.getAuthenticationInfos(token);
        securityService.checkIfUserProprietaireInGroup(userInfos.getId(), groupId);
        var email = equipeService.accepteJoinDemand(memberId, groupId, accepted);
        mailService.sendAcceptJoin(email, accepted);
        return ResponseEntity.ok(Map.of("message", accepted ? "Vous avez bien accepté la demande" : "Vous avez bien refusé la demande"));
    }

}
