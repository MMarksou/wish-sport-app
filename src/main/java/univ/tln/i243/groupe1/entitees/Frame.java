package univ.tln.i243.groupe1.entitees;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Classe qui représente une frame (une liste de jointure à un temps donné).
 */
@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(name = "Frame.rechercherTout",query = "select c from Frame c")
@NamedQuery(name = "Frame.rechercherParEnregistrement",query = "select c from Frame c where c.enregistrement=:enregistrement")
@Table(name = "Frame",uniqueConstraints = {@UniqueConstraint(name = "uniqueFrameEnregistrement",columnNames = {"frameNumero","id_enregistrement"})})
@JsonIgnoreProperties(value = "id", allowSetters = true)
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
    @JsonIgnore
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
