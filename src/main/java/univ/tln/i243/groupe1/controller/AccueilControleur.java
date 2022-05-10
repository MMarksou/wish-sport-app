package univ.tln.i243.groupe1.controller;

import javafx.event.ActionEvent;
import univ.tln.i243.groupe1.JME;

import java.io.IOException;

public class AccueilControleur implements PageControleur {

    public void chargerCategorie(ActionEvent actionEvent) throws IOException {
        chargerPage(actionEvent, "pageChoix.fxml");
    }

    public void chargerJMonkey(ActionEvent actionEvent){
        JME.main(null);
    }
}
