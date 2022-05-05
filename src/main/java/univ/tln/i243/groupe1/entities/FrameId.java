package univ.tln.i243.groupe1.entities;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@EqualsAndHashCode
public class FrameId implements Serializable {
    @Getter
    @Setter
    private  int numero;

    @Getter
    @Setter
    private long enreg;

    public FrameId(int numero, long enreg) {
        this.numero=numero;
        this.enreg=enreg;
    }

}
