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
@Table(name = "Frame",uniqueConstraints = {@UniqueConstraint(name = "uniqueFrameEnregistrement",columnNames = {"numero","enregistrement"})})
public class Frame implements Entite{

    @Id
    @GeneratedValue
    private long id;

    @Setter
    private int numero;

    @Setter
    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "frame")
    private List<Jointure> jointures;

    @Setter
    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "ID_ENREGISTREMENT")
    @JsonIdentityReference(alwaysAsId = true)
    private Enregistrement enregistrement;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Frame frame = (Frame) o;

        if (numero != frame.numero) return false;
        return enregistrement.equals(frame.enregistrement);
    }

    @Override
    public int hashCode() {
        int result = numero;
        result = 31 * result + enregistrement.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Frame " +
                "id=" + id +
                ", numero=" + numero +
                ", jointures=" + jointures +
                ", enregistrement=" + enregistrement;
    }
}
