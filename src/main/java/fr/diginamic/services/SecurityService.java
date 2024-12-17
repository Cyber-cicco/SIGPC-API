package fr.diginamic.services;

import fr.diginamic.config.JwtService;
import fr.diginamic.entities.ProjetUtilisateur;
import fr.diginamic.entities.enums.EquipeRoleEnum;
import fr.diginamic.entities.enums.ProjetRoleEnum;
import fr.diginamic.exception.UnauthorizedException;
import fr.diginamic.equipe.EquipeUtilisateurRepository;
import fr.diginamic.projet.ProjetUtilisateurRepository;
import fr.diginamic.shared.AuthenticationInfos;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SecurityService {

    private final JwtService jwtService;
    private final EquipeUtilisateurRepository equipeUtilisateurRepository;
    private final ProjetUtilisateurRepository projetUtilisateurRepository;
    public AuthenticationInfos getAuthenticationInfos(String token){
        var claims = jwtService.extractAllClaims(token);
        return AuthenticationInfos.builder()
                .id(jwtService.extractId(claims))
                .email(jwtService.extractEmail(claims))
                .roles(jwtService.extractRoles(claims))
                .build();
    }

    public void checkIfUserAllowedInGroup(Long utilisateurId, Long groupId) {
        // TODO : ajouter un cache pour savoir s'il l'utilisateur a les droits (une hashmap)
        var exists = equipeUtilisateurRepository.existsByUtilisateur_IdAndEquipe_Id(utilisateurId, groupId);
        if (!exists) {
            throw new UnauthorizedException();
        }
    }

    /**
     * Renvoie une exception dans le cas où l'utilisateur n'est pas propriétaire du groupe
     * @param userId l'identifiant unique de l'utilisateur connecté
     * @param groupId l'indentifiant du groupe auquel il est rattaché
     */
    public void checkIfUserProprietaireInGroup(Long userId, Long groupId) {
        if (equipeUtilisateurRepository.existsByUtilisateur_IdAndEquipe_IdAndRole(userId, groupId, EquipeRoleEnum.PROPRIETAIRE)) {
            throw new UnauthorizedException();
        }
    }

    public ProjetUtilisateur checkIfUserMemberOfProject(Long userId, Long projectId) {
        var projetUtilisateur = projetUtilisateurRepository.findByUtilisateur_IdAndProjet_Id(userId, projectId)
                .orElseThrow(UnauthorizedException::new);
        return projetUtilisateur;
    }

    public void shouldNotBeVisiteurOfProject(ProjetUtilisateur projetUtilisateur) {
        if (projetUtilisateur.getRole().equals(ProjetRoleEnum.VISITEUR)) {
            throw new UnauthorizedException();
        }
    }
}
