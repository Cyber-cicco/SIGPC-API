package fr.diginamic.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TacheDto {

    private Long id;
    private String nom;
    private String description;
    private boolean done;
    private Date dateDebut;
    private Date dateFin;
    private Date finEstime;
    private Long userStoryId;

}
