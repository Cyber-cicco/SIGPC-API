package fr.diginamic.shared;

import fr.diginamic.entities.enums.RoleEnum;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthenticationInfos {
    private String email;
    private Long id;
    @Builder.Default
    private List<String> roles = new ArrayList<>();
}
