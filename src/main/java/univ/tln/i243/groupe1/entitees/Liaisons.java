package univ.tln.i243.groupe1.entitees;

import javafx.util.Pair;
import lombok.Getter;


import java.util.ArrayList;
import java.util.List;

/**
 * Classe qui contient la liste des liaisons posssibles d'un squelette
 */
@Getter
public class Liaisons {

    private List<Pair<String, String>> listeLiaisons = new ArrayList<>();

    /**
     * Constructeur.
     */
    public Liaisons() {

        listeLiaisons.add(new Pair<>(NomJointures.COLONNE_NOMBRIL.name(), NomJointures.BASSIN.name()));
        listeLiaisons.add(new Pair<>(NomJointures.COLONNE_THORAX.name(), NomJointures.COLONNE_NOMBRIL.name()));
        listeLiaisons.add(new Pair<>(NomJointures.COU.name(), NomJointures.COLONNE_THORAX.name()));
        listeLiaisons.add(new Pair<>(NomJointures.CLAVICULE_GAUCHE.name(), NomJointures.COLONNE_THORAX.name()));
        listeLiaisons.add(new Pair<>(NomJointures.EPAULE_GAUCHE.name(), NomJointures.CLAVICULE_GAUCHE.name()));
        listeLiaisons.add(new Pair<>(NomJointures.COUDE_GAUCHE.name(), NomJointures.EPAULE_GAUCHE.name()));
        listeLiaisons.add(new Pair<>(NomJointures.POIGNET_GAUCHE.name(), NomJointures.COUDE_GAUCHE.name()));
        listeLiaisons.add(new Pair<>(NomJointures.MAIN_GAUCHE.name(), NomJointures.POIGNET_GAUCHE.name()));
        listeLiaisons.add(new Pair<>(NomJointures.BOUTMAIN_GAUCHE.name(), NomJointures.MAIN_GAUCHE.name()));
        listeLiaisons.add(new Pair<>(NomJointures.POUCE_GAUCHE.name(), NomJointures.POIGNET_GAUCHE.name()));
        listeLiaisons.add(new Pair<>(NomJointures.CLAVICULE_DROITE.name(), NomJointures.COLONNE_THORAX.name()));
        listeLiaisons.add(new Pair<>(NomJointures.EPAULE_DROITE.name(), NomJointures.CLAVICULE_DROITE.name()));
        listeLiaisons.add(new Pair<>(NomJointures.COUDE_DROIT.name(), NomJointures.EPAULE_DROITE.name()));
        listeLiaisons.add(new Pair<>(NomJointures.POIGNET_DROIT.name(), NomJointures.COUDE_DROIT.name()));
        listeLiaisons.add(new Pair<>(NomJointures.MAIN_DROITE.name(), NomJointures.POIGNET_DROIT.name()));
        listeLiaisons.add(new Pair<>(NomJointures.BOUTMAIN_DROITE.name(), NomJointures.MAIN_DROITE.name()));
        listeLiaisons.add(new Pair<>(NomJointures.POUCE_DROIT.name(), NomJointures.POIGNET_DROIT.name()));
        listeLiaisons.add(new Pair<>(NomJointures.HANCHE_GAUCHE.name(), NomJointures.BASSIN.name()));
        listeLiaisons.add(new Pair<>(NomJointures.GENOUX_GAUCHE.name(), NomJointures.HANCHE_GAUCHE.name()));
        listeLiaisons.add(new Pair<>(NomJointures.CHEVILLE_GAUCHE.name(), NomJointures.GENOUX_GAUCHE.name()));
        listeLiaisons.add(new Pair<>(NomJointures.PIED_GAUCHE.name(), NomJointures.CHEVILLE_GAUCHE.name()));
        listeLiaisons.add(new Pair<>(NomJointures.HANCHE_DROITE.name(), NomJointures.BASSIN.name()));
        listeLiaisons.add(new Pair<>(NomJointures.GENOUX_DROIT.name(), NomJointures.HANCHE_DROITE.name()));
        listeLiaisons.add(new Pair<>(NomJointures.CHEVILLE_DROITE.name(), NomJointures.GENOUX_DROIT.name()));
        listeLiaisons.add(new Pair<>(NomJointures.PIED_DROIT.name(), NomJointures.CHEVILLE_DROITE.name()));
        listeLiaisons.add(new Pair<>(NomJointures.TETE.name(), NomJointures.COU.name()));
    }

}
