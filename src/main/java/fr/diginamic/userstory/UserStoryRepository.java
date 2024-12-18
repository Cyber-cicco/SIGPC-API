package fr.diginamic.userstory;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.diginamic.entities.UserStory;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserStoryRepository extends JpaRepository<UserStory, Long> {
    @Query(nativeQuery = true, value = "select max(id) from user_story")
    Optional<Long> getMaxId();
}
