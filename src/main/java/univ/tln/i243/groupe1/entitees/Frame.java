package univ.tln.i243.groupe1.entitees;

import lombok.*;
import lombok.extern.java.Log;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
@Log
@NamedQueries({
        @NamedQuery(name = "frame.findAll", query = "select frame from Frame frame"),
        @NamedQuery(name = "frame.findByEnregistrement", query = "select frame from Frame frame where frame.enregistrement = :enreg")
})
@Setter
@Getter
@IdClass(FrameId.class)
public class Frame implements Serializable {

    @Id
    int numero;

    @Id
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumns(
            {
                    @JoinColumn(name = "ENREGISTREMENT_ID",referencedColumnName = "NOM"),
                    @JoinColumn(name = "CATEGORIE_ID",referencedColumnName = "CATEGORIE_ID")

            })
    Enregistrement enregistrement;

    @OneToMany(mappedBy="frame",cascade = {CascadeType.ALL})
    List<Jointure> jointures;
}
