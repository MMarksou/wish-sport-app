package univ.tln.i243.groupe1.entitees;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 *Classe qui représente un ensemble d'enregistrement sous un même nom (ex : catégorie squat)
 */

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
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

    @Setter
    @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE, CascadeType.REMOVE},mappedBy = "categorie")
    private List<MouvementRef> mouvementsRefs;

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
                ", nom='" + nom +
                ", description='" + description +
                ", enregistrements=" + enregistrements;
    }
}
