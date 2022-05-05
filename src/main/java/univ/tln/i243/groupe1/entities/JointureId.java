package univ.tln.i243.groupe1.entities;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@EqualsAndHashCode
public class JointureId implements Serializable {
    @Getter
    @Setter
    private  String id;

    @Getter
    @Setter
    private FrameId frame;

    public JointureId(String id, FrameId frame) {
        this.id=id;
        this.frame=frame;
    }


}