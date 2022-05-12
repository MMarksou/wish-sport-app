package univ.tln.i243.groupe1.entitees;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
@NamedQuery(name = "Frame.rechercherTout",query = "select c from Frame c")

@Table(name = "Frame",uniqueConstraints = {@UniqueConstraint(name = "uniqueFrameEnregistrement",columnNames = {"frameNumero","id_enregistrement"})})
public class Frame implements Entitee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Setter
    private int frameNumero;

    @Setter
    @OrderBy()
    @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE, CascadeType.REMOVE}, mappedBy = "frame")
    private List<Jointure> jointures;

    @Setter
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name = "ID_ENREGISTREMENT")
    @JsonIdentityReference(alwaysAsId = true)
    private Enregistrement enregistrement;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Frame frame = (Frame) o;

        if (frameNumero != frame.frameNumero) return false;
        return enregistrement.equals(frame.enregistrement);
    }

    @Override
    public int hashCode() {
        int result = frameNumero;
        result = 31 * result + enregistrement.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Frame " +
                "id=" + id +
                ", numero=" + frameNumero +
                ", jointures=" + jointures;
    }
}
