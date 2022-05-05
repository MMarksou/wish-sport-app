package univ.tln.i243.groupe1.entitees;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;
import lombok.extern.java.Log;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "ENREGISTREMENT")
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
@Log
@Setter
@Getter
@NamedQueries({
        @NamedQuery(name = "enregistrement.findAll", query = "select enregistrement from Enregistrement enregistrement"),
        @NamedQuery(name = "enregistrement.findByCategorie", query = "select enregistrement from Enregistrement enregistrement where enregistrement.categorie = :cat")
})
@IdClass(EnregistrementId.class)
public class Enregistrement implements Serializable {


    @Id
    String nom;

    @Column(nullable = false)
    int repetition;

    @Column(nullable = false)
    int duree;

    @Column(name = "DESCRIPTION")
    String description;

    @Id
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name="CATEGORIE_ID", referencedColumnName = "CATEGORIE_ID")
    Categorie categorie;

    @OneToMany(mappedBy="enregistrement",cascade = {CascadeType.ALL})
    List<Frame> frames;

}
