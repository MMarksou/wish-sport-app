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

    @Getter
    @Setter
    @ManyToOne
    @JsonIdentityReference(alwaysAsId = true)
    @JoinColumn(name="ENREGISTREMENT_ID")
    Enregistrement enreg;

    @OneToMany(mappedBy="frame",cascade = {CascadeType.ALL})
    List<Jointure> jointures;

    public Frame(List<Jointure> jointures){this.jointures=jointures;}



}
