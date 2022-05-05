package univ.tln.i243.groupe1.entitees;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class EnregistrementId implements Serializable {

    private String nom;

    private String categorie;


}
