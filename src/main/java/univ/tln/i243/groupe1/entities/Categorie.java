package univ.tln.i243.groupe1.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.*;
import lombok.extern.java.Log;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "CATEGORIE")
@NoArgsConstructor
@ToString
@Log
@JsonRootName(value = "categorie")
public class Categorie implements Serializable {

    @Id
    @Column(name = "CATEGORIE_ID")
    @JsonProperty("nom")
    @Setter
    @Getter
    String nom;


    @Getter
    @Setter
    @JsonProperty("description")
    @Column(name = "DESCRIPTION", nullable = false)
    String description;

}
