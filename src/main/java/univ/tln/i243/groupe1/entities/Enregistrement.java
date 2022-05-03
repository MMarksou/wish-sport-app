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
public class Enregistrement implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ENREGISTREMENT_ID")
    @JsonProperty("id")
    @Setter
    @Getter
    long id;

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
    int description;


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
