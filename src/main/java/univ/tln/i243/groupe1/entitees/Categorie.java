package univ.tln.i243.groupe1.entitees;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
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
@NamedQuery(name = "Categorie.rechercherTout",query = "select c from Categorie c")
@NamedQuery(name = "Categorie.rechercherParNom",query = "select c from Categorie c where c.nom=:nom")

@Table(name = "Categorie", uniqueConstraints = {@UniqueConstraint(name = "uniqueCategorie",columnNames = ("nom"))})
public class Categorie implements Entitee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Setter
    private String nom;

    @Setter
    private String description;

    @Setter
    @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE, CascadeType.REMOVE},mappedBy = "categorie")
    private List<Enregistrement> enregistrements;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Categorie categorie = (Categorie) o;

        return nom.equals(categorie.nom);
    }

    @Override
    public int hashCode() {
        return nom.hashCode();
    }

    @Override
    public String toString() {
        return "Categorie " +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", enregistrements=" + enregistrements;
    }
}
