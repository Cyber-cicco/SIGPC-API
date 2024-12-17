package fr.diginamic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import fr.diginamic.entities.ProjetUtilisateur;

public interface ProjetUtilisateurRepository extends JpaRepository<ProjetUtilisateur, Long>  {

    Optional<ProjetUtilisateur> findByUtilisateur_IdAndProjet_Id(Long utilisateurId, Long projetId);
}
