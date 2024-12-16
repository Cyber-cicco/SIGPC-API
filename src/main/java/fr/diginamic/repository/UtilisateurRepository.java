package fr.diginamic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

import fr.diginamic.entities.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long>  {
    Boolean existsByEmail(String email);
    void deleteUtilisateurByEmail(String email);
    Optional<Utilisateur> findUtilisateurByEmail(String email);
    Optional<Utilisateur> findByActivationLink(UUID link);
    Optional<Utilisateur> findUtilisateurByEmailAndEmailVerified(String email, Boolean verfied);

}
