package fr.diginamic.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;    
import fr.diginamic.dto.InvitationDto;
import org.springframework.http.ResponseEntity;
import fr.diginamic.services.InvitationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/invitation")
public class InvitationController {

   
    private final InvitationService invitationService;

}