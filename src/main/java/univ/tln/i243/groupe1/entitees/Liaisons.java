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

        String bassin = "BASSIN";
        String thorax = "COLONNE_THORAX";
        String poignetGauche = "POIGNET_GAUCHE";
        String poignetDroit = "POIGNET_DROIT";

        listeLiaisons.add(new Pair<>("COLONNE_NOMBRIL", bassin));
        listeLiaisons.add(new Pair<>(thorax, "COLONNE_NOMBRIL"));
        listeLiaisons.add(new Pair<>("COU", thorax));
        listeLiaisons.add(new Pair<>("CLAVICULE_GAUCHE", thorax));
        listeLiaisons.add(new Pair<>("EPAULE_GAUCHE", "CLAVICULE_GAUCHE"));
        listeLiaisons.add(new Pair<>("COUDE_GAUCHE", "EPAULE_GAUCHE"));
        listeLiaisons.add(new Pair<>(poignetGauche, "COUDE_GAUCHE"));
        listeLiaisons.add(new Pair<>("MAIN_GAUCHE", poignetGauche));
        listeLiaisons.add(new Pair<>("BOUTMAIN_GAUCHE", "MAIN_GAUCHE"));
        listeLiaisons.add(new Pair<>("POUCE_GAUCHE", poignetGauche));
        listeLiaisons.add(new Pair<>("CLAVICULE_DROITE", thorax));
        listeLiaisons.add(new Pair<>("EPAULE_DROITE", "CLAVICULE_DROITE"));
        listeLiaisons.add(new Pair<>("COUDE_DROIT", "EPAULE_DROITE"));
        listeLiaisons.add(new Pair<>(poignetDroit, "COUDE_DROIT"));
        listeLiaisons.add(new Pair<>("MAIN_DROITE", poignetDroit));
        listeLiaisons.add(new Pair<>("BOUTMAIN_DROITE", "MAIN_DROITE"));
        listeLiaisons.add(new Pair<>("POUCE_DROIT", poignetDroit));
        listeLiaisons.add(new Pair<>("HANCHE_GAUCHE", bassin));
        listeLiaisons.add(new Pair<>("GENOUX_GAUCHE", "HANCHE_GAUCHE"));
        listeLiaisons.add(new Pair<>("CHEVILLE_GAUCHE", "GENOUX_GAUCHE"));
        listeLiaisons.add(new Pair<>("PIED_GAUCHE", "CHEVILLE_GAUCHE"));
        listeLiaisons.add(new Pair<>("HANCHE_DROITE", bassin));
        listeLiaisons.add(new Pair<>("GENOUX_DROIT", "HANCHE_DROITE"));
        listeLiaisons.add(new Pair<>("CHEVILLE_DROITE", "GENOUX_DROIT"));
        listeLiaisons.add(new Pair<>("PIED_DROIT", "CHEVILLE_DROITE"));
        listeLiaisons.add(new Pair<>("TETE", "COU"));
    }

}
