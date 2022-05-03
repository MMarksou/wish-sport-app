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
}
