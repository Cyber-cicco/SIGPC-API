package fr.diginamic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.diginamic.entities.UserStory;

public interface UserStoryRepository extends JpaRepository<UserStory, Long> {

}
