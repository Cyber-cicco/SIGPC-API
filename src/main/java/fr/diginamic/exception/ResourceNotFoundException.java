package fr.diginamic.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// TODO : Javadoc à faire
@Getter
@Setter
@NoArgsConstructor
public class ResourceNotFoundException extends APIException {
  private String nomRessource;
  private String champ;
  private String nomChamp;
  private Long idChamp;

  public ResourceNotFoundException(String nomRessource, String champ, Long idChamp) {
    super(String.format("Ressource '%s' avec %s:'%s' non trouvée", nomRessource, champ, idChamp));
    this.nomRessource = nomRessource;
    this.champ = champ;
    this.idChamp = idChamp;
  }
}
