package fr.diginamic.services;

import fr.diginamic.config.JwtService;
import fr.diginamic.shared.AuthenticationInfos;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SecurityService {

    private final JwtService jwtService;
    public AuthenticationInfos getAuthenticationInfos(String token){
        var claims = jwtService.extractAllClaims(token);
        return AuthenticationInfos.builder()
                .id(jwtService.extractId(claims))
                .email(jwtService.extractEmail(claims))
                .roles(jwtService.extractRoles(claims))
                .build();
    }
}
