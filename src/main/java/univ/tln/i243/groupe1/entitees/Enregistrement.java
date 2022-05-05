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
@NamedQueries({
        @NamedQuery(name = "Enregistrement.findALl",query = "select c from Enregistrement c")
})
@Table(name = "Enregistrement",uniqueConstraints = {@UniqueConstraint(name = "uniqueEnregistrementCategorie", columnNames = {"nom","categorie"})})
public class Enregistrement implements Entite{

    @Id
    @GeneratedValue
    private long id;

    @Setter
    private String nom;

    @Setter
    private int durée;

    @Setter
    private String description;

    @Setter
    private int repetition;

    @Setter
    @OneToMany(cascade = {CascadeType.PERSIST},mappedBy = "enregistrement")
    private List<Frame> frames;

    @Setter
    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "ID_CATEGORIE")
    @JsonIdentityReference(alwaysAsId = true)
    private Categorie categorie;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Enregistrement that = (Enregistrement) o;

        if (!nom.equals(that.nom)) return false;
        return categorie.equals(that.categorie);
    }

    @Override
    public int hashCode() {
        int result = nom.hashCode();
        result = 31 * result + categorie.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Enregistrement " +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", durée=" + durée +
                ", description='" + description + '\'' +
                ", repetition=" + repetition +
                ", frames=" + frames +
                ", categorie=" + categorie;
    }
}
