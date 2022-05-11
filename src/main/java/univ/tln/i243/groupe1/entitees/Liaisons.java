package univ.tln.i243.groupe1.entitees;

import javafx.util.Pair;
import lombok.Getter;


import java.util.ArrayList;
import java.util.List;


@Getter
public class Liaisons {

    private List<Pair<String, String>> listeLiaisons = new ArrayList<>();

    public Liaisons() {
        listeLiaisons.add(new Pair<>("COLONNE_NOMBRIL", "BASSIN"));
        listeLiaisons.add(new Pair<>("COLONNE_THORAX", "COLONNE_NOMBRIL"));
        listeLiaisons.add(new Pair<>("COU", "COLONNE_THORAX"));
        listeLiaisons.add(new Pair<>("CLAVICULE_GAUCHE", "COLONNE_THORAX"));
        listeLiaisons.add(new Pair<>("EPAULE_GAUCHE", "CLAVICULE_GAUCHE"));
        listeLiaisons.add(new Pair<>("COUDE_GAUCHE", "EPAULE_GAUCHE"));
        listeLiaisons.add(new Pair<>("POIGNET_GAUCHE", "COUDE_GAUCHE"));
        listeLiaisons.add(new Pair<>("MAIN_GAUCHE", "POIGNET_GAUCHE"));
        listeLiaisons.add(new Pair<>("BOUTMAIN_GAUCHE", "MAIN_GAUCHE"));
        listeLiaisons.add(new Pair<>("POUCE_GAUCHE", "POIGNET_GAUCHE"));
        listeLiaisons.add(new Pair<>("CLAVICULE_DROITE", "COLONNE_THORAX"));
        listeLiaisons.add(new Pair<>("EPAULE_DROITE", "CLAVICULE_DROITE"));
        listeLiaisons.add(new Pair<>("COUDE_DROIT", "EPAULE_DROITE"));
        listeLiaisons.add(new Pair<>("POIGNET_DROIT", "COUDE_DROIT"));
        listeLiaisons.add(new Pair<>("MAIN_DROITE", "POIGNET_DROIT"));
        listeLiaisons.add(new Pair<>("BOUTMAIN_DROITE", "MAIN_DROITE"));
        listeLiaisons.add(new Pair<>("POUCE_DROIT", "POIGNET_DROIT"));
        listeLiaisons.add(new Pair<>("HANCHE_GAUCHE", "BASSIN"));
        listeLiaisons.add(new Pair<>("GENOUX_GAUCHE", "HANCHE_GAUCHE"));
        listeLiaisons.add(new Pair<>("CHEVILLE_GAUCHE", "GENOUX_GAUCHE"));
        listeLiaisons.add(new Pair<>("PIED_GAUCHE", "CHEVILLE_GAUCHE"));
        listeLiaisons.add(new Pair<>("HANCHE_DROITE", "BASSIN"));
        listeLiaisons.add(new Pair<>("GENOUX_DROIT", "HANCHE_DROITE"));
        listeLiaisons.add(new Pair<>("CHEVILLE_DROITE", "GENOUX_DROIT"));
        listeLiaisons.add(new Pair<>("PIED_DROIT", "CHEVILLE_DROITE"));
        listeLiaisons.add(new Pair<>("TETE", "COU"));
    }

}
