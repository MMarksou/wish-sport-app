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

@Entity
@Table(name = "JOINTURE")
@NoArgsConstructor
@ToString
@Log
@JsonRootName(value = "jointure")
@NamedQueries({
        @NamedQuery(name = "jointure.findAll", query = "select jointure from Jointure jointure"),
        @NamedQuery(name = "jointure.findByFrame", query = "select jointure from Jointure jointure where jointure.frame = :frame")
})
public class Jointure implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "JOINTURE_ID")
    @JsonProperty("id")
    @Setter
    @Getter
    long id;

    @JsonProperty("x")
    @Column(name = "X", nullable = false)
    @Setter
    @Getter
    float x;

    @JsonProperty("y")
    @Column(name = "Y", nullable = false)
    @Setter
    @Getter
    float y;

    @JsonProperty("z")
    @Column(name = "Z", nullable = false)
    @Setter
    @Getter
    float z;

    @JsonProperty("w")
    @Column(name = "W", nullable = false)
    @Setter
    @Getter
    float w;

    @JsonProperty("wx")
    @Column(name = "WX", nullable = false)
    @Setter
    @Getter
    float wx;

    @JsonProperty("wy")
    @Column(name = "WY", nullable = false)
    @Setter
    @Getter
    float wy;

    @JsonProperty("wz")
    @Column(name = "WZ", nullable = false)
    @Setter
    @Getter
    float wz;

    @Getter
    @Setter
    @ManyToOne
    @JsonIdentityReference(alwaysAsId = true)
    @JoinColumn(name="FRAME_ID")
    Frame frame;
}
