package fr.diginamic.tache;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.diginamic.entities.Tache;

public interface TacheRepository extends JpaRepository<Tache, Long> {

    
}
