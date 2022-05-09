package univ.tln.i243.groupe1.controller;

import javafx.event.ActionEvent;
import java.io.IOException;

public class AccueilControleur implements PageControleur {

    public void chargerCategorie(ActionEvent actionEvent) throws IOException {
        chargerPage(actionEvent, "pageChoix.fxml");
    }
}
