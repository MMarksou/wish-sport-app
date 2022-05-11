package univ.tln.i243.groupe1.controleur;

import javafx.event.ActionEvent;

import java.io.IOException;

public class AccueilControleur implements PageControleur {

    public void chargerCategorie(ActionEvent actionEvent) throws IOException {
        chargerPage(actionEvent, "pageChoix.fxml");
    }

    public void chargerVisualisation(ActionEvent actionEvent) throws IOException {
        chargerPage(actionEvent,"pageChoixEnregistrement.fxml");

    }
}
