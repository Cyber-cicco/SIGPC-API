package fr.diginamic.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import fr.diginamic.userstory.UserStoryDto;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DocumentDto {

    private Long id;
    private String nom;
    private String extension;
    private UserStoryDto userStory;

}
