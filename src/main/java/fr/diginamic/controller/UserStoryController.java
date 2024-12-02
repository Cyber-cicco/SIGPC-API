package fr.diginamic.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;    
import fr.diginamic.dto.UserStoryDto;
import org.springframework.http.ResponseEntity;
import fr.diginamic.services.UserStoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/userStory")
public class UserStoryController {

   
    private final UserStoryService userStoryService;

}
