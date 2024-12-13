package fr.diginamic.repository;

import fr.diginamic.entities.EquipeUtilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipeUtilisateurRepository extends JpaRepository<EquipeUtilisateur,Long>{
    Boolean existsByUtilisateur_IdAndEquipe_Id(Long utilisateurId, Long equipeId);
}

