package fr.diginamic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import fr.diginamic.entities.Ressource;

public interface RessourceRepository extends JpaRepository<Ressource, Long>  {

}
