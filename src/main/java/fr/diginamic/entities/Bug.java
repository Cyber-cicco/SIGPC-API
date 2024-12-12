package fr.diginamic.entities;

import fr.diginamic.entities.enums.SeveriteEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Bug {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    @Enumerated
    private SeveriteEnum severite;

    @ManyToOne
    @JoinColumn(name = "user_story_id")
    private UserStory userStory;
}
