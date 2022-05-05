package univ.tln.i243.groupe1.entitees;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.extern.java.Log;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Log
@Setter
@Getter
@NamedQueries({
        @NamedQuery(name = "categorie.findAll", query = "select categorie from Categorie categorie")})
public class Categorie implements Serializable {


    @Id
    @Column(name = "CATEGORIE_ID")
    @JsonProperty("nom")
    String nom;

    @OneToMany(mappedBy= "categorie",cascade = {CascadeType.ALL})
    List<Enregistrement> enregistrements;


    @Column(name = "DESCRIPTION", nullable = false)
    String description;

    public void addEnregistrement(Enregistrement enregistrement){
        this.enregistrements.add(enregistrement);
    }

}
