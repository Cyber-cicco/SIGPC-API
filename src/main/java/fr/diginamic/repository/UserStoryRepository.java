package fr.diginamic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import fr.diginamic.entities.UserStory;

public interface UserStoryRepository extends JpaRepository<UserStory, Long>  {

}
