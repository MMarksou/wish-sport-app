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
@Table(name = "TypeExercice")
@NoArgsConstructor
@ToString
@Log
@JsonRootName(value = "typeexercice")
public class TypeExercice implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "TYPEEXERCICE_ID")
    @JsonProperty("id")
    @Setter
    @Getter
    long id;

    @JsonProperty("serie")
    @Column(name = "SERIE", nullable = false)
    @Setter
    @Getter
    int serie;

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

    @JsonProperty("nom")
    @Column(name = "NOM", nullable = false)
    @Setter
    @Getter
    int nom;

    @JsonProperty("description")
    @Column(name = "DESCRIPTION", nullable = false)
    @Setter
    @Getter
    int description;
}
