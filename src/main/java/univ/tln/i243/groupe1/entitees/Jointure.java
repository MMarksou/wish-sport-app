package univ.tln.i243.groupe1.entitees;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.*;
import lombok.extern.java.Log;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Log
@NamedQueries({
        @NamedQuery(name = "jointure.findAll", query = "select jointure from Jointure jointure"),
        @NamedQuery(name = "jointure.findByFrame", query = "select jointure from Jointure jointure where jointure.frame = :frame")
})
@Setter
@Getter
@IdClass(JointureId.class)
public class Jointure implements Serializable {
    @Id
    String id;

    @Column(nullable = false)
    float x;
    
    @Column(nullable = false)
    float y;

    @Column(nullable = false)
    float z;

    @Column(nullable = false)
    float w;

    @Column(nullable = false)
    float wx;

    @Column(nullable = false)
    float wy;

    @Column(nullable = false)
    float wz;

    @Id
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumns(
    {
            @JoinColumn(name="FRAME_ID", referencedColumnName="NUMERO"),
            @JoinColumn(name="ENREGISTREMENT_ID", referencedColumnName = "ENREGISTREMENT_ID"),
            @JoinColumn(name = "CATEGORIE_ID",referencedColumnName = "CATEGORIE_ID")
    })
    Frame frame;


    @Column(nullable = false)
    String nom;
}
