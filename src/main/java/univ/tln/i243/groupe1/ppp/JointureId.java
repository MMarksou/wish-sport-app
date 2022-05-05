package univ.tln.i243.groupe1.ppp;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@EqualsAndHashCode
public class JointureId implements Serializable {
    @Getter
    @Setter
    private  String id;

    @Getter
    @Setter
    private Frame frame;


}