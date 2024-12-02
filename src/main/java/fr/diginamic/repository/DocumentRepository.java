package fr.diginamic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import fr.diginamic.entities.Document;

public interface DocumentRepository extends JpaRepository<Document, Long>  {

}
