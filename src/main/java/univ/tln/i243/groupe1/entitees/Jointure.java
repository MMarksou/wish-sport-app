package univ.tln.i243.groupe1.entitees;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
@Table(name = "Jointure",uniqueConstraints = {@UniqueConstraint(name = "uniqueJointureFrame",columnNames = {"nom","frame"})})
public class Jointure implements Entite{

    @Id
    @GeneratedValue
    private long id;

    @Setter
    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "ID_FRAME")
    @JsonIdentityReference(alwaysAsId = true)
    private Frame frame;

    @Setter
    private String nom;

    @Setter
    private long w;

    @Setter
    private long wx;

    @Setter
    private long wy;

    @Setter
    private long wz;

    @Setter
    private long x;

    @Setter
    private long y;

    @Setter
    private long z;


    @Override
    public String toString() {
        return "Jointure " +
                "id=" + id +
                ", frame=" + frame +
                ", nom='" + nom + '\'' +
                ", w=" + w +
                ", wx=" + wx +
                ", wy=" + wy +
                ", wz=" + wz +
                ", x=" + x +
                ", y=" + y +
                ", z=" + z;
    }
}
