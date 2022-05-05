package univ.tln.i243.groupe1.controller;


import javafx.event.ActionEvent;

import java.io.IOException;

public class ChoixControleur implements PageControleur {

    /**
     * Cette méthode permet de charger la page de création d'une catégorie
     */

    public void chargerCategorie(ActionEvent actionEvent) throws IOException {
        chargerPage(actionEvent, "pageAjouterCategorie.fxml");
    }
    public void chargerEnregistrement(ActionEvent actionEvent) throws IOException {
        chargerPage(actionEvent, "pageAjouterEnregistrement.fxml");
    }
    public void retourAccueil(ActionEvent actionEvent) throws IOException {
        chargerPage(actionEvent, "pageAccueil.fxml");
    }
}
