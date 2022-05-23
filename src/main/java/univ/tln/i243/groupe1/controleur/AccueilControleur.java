package univ.tln.i243.groupe1.controleur;

import javafx.event.ActionEvent;

import java.io.IOException;

/**
 *Classe de contrôle de l'accueil
 */

public class AccueilControleur implements PageControleur {

    /**
     * Charge la page des créations
     * @param actionEvent action event
     * @throws IOException erreur fichier
     */
    public void chargerCategorie(ActionEvent actionEvent) throws IOException {
        chargerPage(actionEvent, "pageChoix.fxml");
    }

    /**
     * Charge la page de la liste des exercices pour visualiser ou supprimer.
     * @param actionEvent action event
     * @throws IOException erreur fichier
     */
    public void chargerVisualisation(ActionEvent actionEvent) throws IOException {
        chargerPage(actionEvent,"pageChoixEnregistrement.fxml");

    }

    /**
     * Charge la page de la liste des exercices pour comparer avec un mouvement de référence
     * @param actionEvent action event
     * @throws IOException erreur fichier
     */
    public void chargerComparaison(ActionEvent actionEvent) throws IOException {
        chargerPage(actionEvent,"pageChoixComparaison.fxml");
    }
}
