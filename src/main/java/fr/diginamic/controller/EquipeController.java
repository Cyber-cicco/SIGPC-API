package fr.diginamic.controller;

import brevo.ApiException;
import fr.diginamic.dto.EquipeDto;
import fr.diginamic.dto.EquipeTransformer;
import fr.diginamic.dto.InvitationDto;
import fr.diginamic.dto.SimpleInvitationDto;
import fr.diginamic.entities.Equipe;
import fr.diginamic.services.MailService;
import fr.diginamic.services.SecurityService;
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
    public ResponseEntity<?> postInvite(@CookieValue("AUTH-TOKEN") String token, @PathVariable("groupId") Long groupId, @RequestBody SimpleInvitationDto invitationDto) {
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

}
