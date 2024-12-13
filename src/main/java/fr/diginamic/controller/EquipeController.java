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

    @PostMapping
    public ResponseEntity<EquipeDto> postNewEquipe(@CookieValue("AUTH-TOKEN") String token, @RequestBody EquipeDto equipeDto) {
        var userInfos = securityService.getAuthenticationInfos(token);
        Equipe equipe = equipeService.postNewEquipe(userInfos, equipeDto);
        return ResponseEntity.ok(equipeTransformer.toequipeDto(equipe));
    }

    @PostMapping("/{groupId}/invite")
    public ResponseEntity<?> postInvite(@CookieValue("AUTH-TOKEN") String token, @PathVariable("groupId") Long groupId, @RequestBody SimpleInvitationDto invitationDto) {
        var userInfos = securityService.getAuthenticationInfos(token);
        securityService.checkIfUserAllowedInGroup(userInfos.getId(), groupId);
        equipeService.postInvite(invitationDto, groupId, userInfos);
        try {
            mailService.sendInvitation(invitationDto);
        } catch (ApiException e) {
            equipeService.removeInvite(invitationDto, groupId);
        }
        return ResponseEntity.ok(Map.of("message", "invite sent"));
    }

}
