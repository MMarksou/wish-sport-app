package univ.tln.i243.groupe1.ppp;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@EqualsAndHashCode
public class FrameId implements Serializable {
    @Getter
    @Setter
    private  int numero;

    @Getter
    @Setter
    private long id;


}
