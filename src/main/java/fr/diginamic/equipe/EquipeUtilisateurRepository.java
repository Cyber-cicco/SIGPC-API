package fr.diginamic.equipe;

import fr.diginamic.entities.EquipeUtilisateur;
import fr.diginamic.entities.enums.EquipeRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EquipeUtilisateurRepository extends JpaRepository<EquipeUtilisateur,Long>{
    Boolean existsByUtilisateur_IdAndEquipe_Id(Long utilisateurId, Long equipeId);
    Boolean existsByUtilisateur_IdAndEquipe_IdAndRole(Long utilisateurId, Long equipeId, EquipeRoleEnum role);
    Optional<EquipeUtilisateur> findByUtilisateur_IdAndEquipe_Id(Long utilisateurId, Long equipeId);
}

