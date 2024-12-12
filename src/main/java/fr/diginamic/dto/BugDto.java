package fr.diginamic.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class BugDto {
    private Long id;
    private String descrption;

}
