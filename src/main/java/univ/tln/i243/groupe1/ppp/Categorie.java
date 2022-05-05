package univ.tln.i243.groupe1.ppp;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.*;
import lombok.extern.java.Log;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "CATEGORIE")
@NoArgsConstructor
@ToString
@Log
@JsonRootName(value = "categorie")
@NamedQueries({
        @NamedQuery(name = "categorie.findAll", query = "select categorie from Categorie categorie"),
/*        @NamedQuery(name = "personne.findByComposeId", query = "select personne from Personne personne where personne.nom = :nom and personne.prenom = :prenom"),
        @NamedQuery(name = "personne.update", query = "update Personne personne set personne.nom = :nom, personne.prenom = :prenom, personne.age = :age where personne.nom = :nom and personne.prenom = :prenom")*/

})
public class Categorie implements Serializable {


    @Id
    @Column(name = "CATEGORIE_ID")
    @JsonProperty("nom")
    @Setter
    @Getter
    String nom;

    @OneToMany(mappedBy="cat",cascade = {CascadeType.ALL})
    List<Enregistrement> enregistrements;


    @Getter
    @Setter
    @JsonProperty("description")
    @Column(name = "DESCRIPTION", nullable = false)
    String description;

    public Categorie(List<Enregistrement> enregistrements){this.enregistrements=enregistrements;}

}
