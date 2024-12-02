package fr.diginamic.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;    
import fr.diginamic.dto.TentativeSupressionMdpDto;
import org.springframework.http.ResponseEntity;
import fr.diginamic.services.TentativeSupressionMdpService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/tentativeSupressionMdp")
public class TentativeSupressionMdpController {

   
    private final TentativeSupressionMdpService tentativeSupressionMdpService;

}
