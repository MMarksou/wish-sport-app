package univ.tln.i243.groupe1.entitees;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

/**
 *Classe qui représente un mouvement reference par rapport a une categorie
 */

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(name = "MouvementRef.rechercherTout",query = "select m from MouvementRef m")
@NamedQuery(name = "MouvementRef.rechercherParCategorie",query = "select m from MouvementRef m where m.categorie=:categorie")

@Table(name = "MouvementRef", uniqueConstraints = {@UniqueConstraint(name = "uniqueMouvementRef",columnNames = {"jointure2","id_categorie"})})
public class MouvementRef implements Entitee{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Setter
    private String jointure1;

    @Setter
    private String jointure2;

    @Setter
    private String jointure3;

    @Setter
    private double angleDebut;

    @Setter
    private double andgleFin;

    @Setter
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "ID_CATEGORIE")
    @JsonIdentityReference(alwaysAsId = true)
    private Categorie categorie;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MouvementRef)) return false;
        MouvementRef that = (MouvementRef) o;
        return getJointure2().equals(that.getJointure2()) && getCategorie().equals(that.getCategorie());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getJointure2(), getCategorie());
    }

    @Override
    public String toString() {
        return "MouvementRef{" +
                "id=" + id +
                ", jointure1='" + jointure1 + '\'' +
                ", jointure2='" + jointure2 + '\'' +
                ", jointure3='" + jointure3 + '\'' +
                ", categorie=" + categorie +
                '}';
    }
}
