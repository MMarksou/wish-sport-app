package univ.tln.i243.groupe1.entitees;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class FrameId implements Serializable {
    private  int numero;

    private EnregistrementId enregistrement;

}
