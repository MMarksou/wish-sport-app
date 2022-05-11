package univ.tln.i243.groupe1.entitees;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
@NamedQuery(name = "Enregistrement.rechercherTout",query = "select c from Enregistrement c")
@NamedQuery(name = "Enregistrement.rechercherParNom",query = "select c from Enregistrement c where c.nom=:nom")

@Table(name = "Enregistrement",uniqueConstraints = {@UniqueConstraint(name = "uniqueEnregistrementCategorie", columnNames = {"nom","id_categorie"})})
public class Enregistrement implements Entitee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Setter
    private String nom;

    @Setter
    private int duree;

    @Setter
    private String description;

    @Setter
    private int repetition;

    @Setter
    @OrderBy("frameNumero ASC")
    @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE},mappedBy = "enregistrement")
    private List<Frame> frames = new ArrayList<>();

    @Setter
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
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
                ", durée=" + duree +
                ", description='" + description + '\'' +
                ", repetition=" + repetition +
                ", frames=" + frames;
    }

    public void ajouterFrame(Frame frame){
        this.frames.add(frame);
    }
}
