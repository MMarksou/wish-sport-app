package univ.tln.i243.groupe1.controller;

import javafx.event.ActionEvent;

import java.io.IOException;

public class AjouterEnregistrementControleur implements PageControleur{

    public void validerEnregistrement(ActionEvent actionEvent) throws IOException {
        chargerPage(actionEvent, "pageEnregistrement.fxml");
    }
    public void retourChoix(ActionEvent actionEvent) throws IOException {
        chargerPage(actionEvent, "pageChoix.fxml");
    }
}
