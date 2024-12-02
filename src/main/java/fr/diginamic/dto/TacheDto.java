package fr.diginamic.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import fr.diginamic.dto.UserStoryDto;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TacheDto {

    private Long id;
    private String nom;
    private String description;
    private boolean done;
    private UserStoryDto userStory;

}
