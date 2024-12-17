package fr.diginamic.repository;

import fr.diginamic.entities.enums.TypeInvitationEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import fr.diginamic.entities.Invitation;

import javax.swing.text.html.Option;

public interface InvitationRepository extends JpaRepository<Invitation, Long>  {

    void deleteByUtilisateur_EmailAndEquipe_Id(String email, Long idGroup);
    Optional<Invitation> findFirstByUtilisateur_IdAndEquipe_IdAndTypeInvitationOrderByDateEmissionDesc(Long userId, Long equipeId, TypeInvitationEnum type);
}
