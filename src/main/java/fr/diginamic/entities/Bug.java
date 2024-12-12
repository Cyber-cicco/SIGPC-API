package fr.diginamic.entities;

import fr.diginamic.entities.enums.SeveriteEnum;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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
