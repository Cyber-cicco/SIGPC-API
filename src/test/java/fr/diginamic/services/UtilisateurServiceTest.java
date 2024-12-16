package fr.diginamic.services;

import fr.diginamic.dto.CompteDto;
import fr.diginamic.entities.Utilisateur;
import fr.diginamic.repository.UtilisateurRepository;
import jakarta.xml.bind.ValidationException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class UtilisateurServiceTest {

    @Autowired
    @InjectMocks
    private UtilisateurService utilisateurService;

    @Spy
    private UtilisateurRepository utilisateurRepository;

    @Test
    void createAccount() {
        utilisateurRepository =  Mockito.spy(UtilisateurRepository.class);
        Mockito.doReturn(new Utilisateur()).when(utilisateurRepository).save(Mockito.any(Utilisateur.class));
        var compte1 = CompteDto.builder()
                .build();
        assertThrows(ValidationException.class, () -> utilisateurService.createAccount(compte1));
    }
}