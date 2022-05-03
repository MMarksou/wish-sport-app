package univ.tln.i243.groupe1.entities;

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
@Table(name = "FRAME")
@NoArgsConstructor
@ToString
@Log
@JsonRootName(value = "frame")
public class Frame implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "FRAME_ID")
    @JsonProperty("id")
    @Setter
    @Getter
    long id;

    @JsonProperty("numero")
    @Column(name = "NUMERO", nullable = false)
    @Setter
    @Getter
    int numero;

    /** ------donnee JSON a discuter !!------**/

}
