package fr.diginamic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import fr.diginamic.entities.Commentaire;

public interface CommentaireRepository extends JpaRepository<Commentaire, Long>  {

}
