package fr.diginamic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import fr.diginamic.entities.Invitation;

public interface InvitationRepository extends JpaRepository<Invitation, Long>  {

}
