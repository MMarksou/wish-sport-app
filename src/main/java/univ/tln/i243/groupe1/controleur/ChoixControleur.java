package univ.tln.i243.groupe1.controleur;


import javafx.event.ActionEvent;

import java.io.IOException;

/**
 * Classe contrôleur permettant de charger la page de création d'une catégorie
 */
public class ChoixControleur implements PageControleur {

    /**
     * Charge la page de création de catégorie.
     * @param actionEvent action event
     * @throws IOException erreur fichier
     */
    public void chargerCategorie(ActionEvent actionEvent) throws IOException {
        chargerPage(actionEvent, "pageAjouterCategorie.fxml");
    }

    /**
     * Charge la page de création d'enregistrement
     * @param actionEvent action event
     * @throws IOException erreur fichier
     */
    public void chargerEnregistrement(ActionEvent actionEvent) throws IOException {
        chargerPage(actionEvent, "pageAjouterEnregistrement.fxml");
    }

    public void chargerExerciceReferent(ActionEvent actionEvent) throws IOException {
        chargerPage(actionEvent, "pageAjouterExerciceReferent.fxml");
    }

    /**
     * Retour à l'accueil
     * @param actionEvent action event
     * @throws IOException erreur fichier
     */
    public void retourAccueil(ActionEvent actionEvent) throws IOException {
        chargerPage(actionEvent, "pageAccueil.fxml");
    }


}
