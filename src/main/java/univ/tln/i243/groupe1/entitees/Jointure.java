package univ.tln.i243.groupe1.entitees;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;

/**
 * Classe qui représente une jointure (une articulation du squelette).
 */
@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
@NamedQuery(name = "Jointure.rechercherTout",query = "select c from Jointure c")

@Table(name = "Jointure",uniqueConstraints = {@UniqueConstraint(name = "uniqueJointureFrame",columnNames = {"nom","id_frame"})})
public class Jointure implements Entitee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Setter
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name = "ID_FRAME")
    @JsonIdentityReference(alwaysAsId = true)
    private Frame frame;

    @Setter
    private String nom;

    @Setter
    private float w;

    @Setter
    private float wx;

    @Setter
    private float wy;

    @Setter
    private float wz;

    @Setter
    private float x;

    @Setter
    private float y;

    @Setter
    private float z;


    @Override
    public String toString() {
        return "Jointure " +
                "id=" + id +
                ", nom='" + nom +
                ", w=" + w +
                ", wx=" + wx +
                ", wy=" + wy +
                ", wz=" + wz +
                ", x=" + x +
                ", y=" + y +
                ", z=" + z;
    }
}
