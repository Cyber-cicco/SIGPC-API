package fr.diginamic.services;

import fr.diginamic.config.JwtService;
import fr.diginamic.exception.UnauthorizedException;
import fr.diginamic.repository.EquipeUtilisateurRepository;
import fr.diginamic.shared.AuthenticationInfos;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SecurityService {

    private final JwtService jwtService;
    private final EquipeUtilisateurRepository equipeUtilisateurRepository;
    public AuthenticationInfos getAuthenticationInfos(String token){
        var claims = jwtService.extractAllClaims(token);
        return AuthenticationInfos.builder()
                .id(jwtService.extractId(claims))
                .email(jwtService.extractEmail(claims))
                .roles(jwtService.extractRoles(claims))
                .build();
    }

    public void checkIfUserAllowedInGroup(Long utilisateurId, Long groupId) {
        var exists = equipeUtilisateurRepository.existsByUtilisateur_IdAndEquipe_Id(utilisateurId, groupId);
        if (!exists) {
            throw new UnauthorizedException();
        }
    }
}
