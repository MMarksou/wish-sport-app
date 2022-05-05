package univ.tln.i243.groupe1.entities;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.java.Log;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "ENREGISTREMENT")
@NoArgsConstructor
@ToString
@Log
@JsonRootName(value = "enregistrement")
@NamedQueries({
        @NamedQuery(name = "enregistrement.findAll", query = "select enregistrement from Enregistrement enregistrement"),
        @NamedQuery(name = "enregistrement.findByCategorie", query = "select enregistrement from Enregistrement enregistrement where enregistrement.cat = :cat")
})
public class Enregistrement implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ENREGISTREMENT_ID")
    @JsonProperty("id")
    @Setter
    @Getter
    long id;

    @Column(name = "NOM")
    @JsonProperty("nom")
    @Setter
    @Getter
    String nom;

    @JsonProperty("repetition")
    @Column(name = "REPETITION", nullable = false)
    @Setter
    @Getter
    int repetition;

    @JsonProperty("duree")
    @Column(name = "DUREE", nullable = false)
    @Setter
    @Getter
    int duree;

    @JsonProperty("description")
    @Column(name = "DESCRIPTION")
    @Setter
    @Getter
    String description;


    @Getter
    @Setter
    @ManyToOne
    @JsonIdentityReference(alwaysAsId = true)
    @JoinColumn(name="CATEGORIE_ID")
    Categorie cat;

    @OneToMany(mappedBy="enreg",cascade = {CascadeType.ALL})
    List<Frame> frames;

    public Enregistrement(List<Frame> frames){this.frames=frames;}

}
