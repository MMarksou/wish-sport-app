package univ.tln.i243.groupe1.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class CategorieController {

    /**
     * Cette méthode permet de charger la page de création d'une catégorie
     */
    public void chargerPage(ActionEvent actionEvent) throws IOException {
        AddExerciceController str = new AddExerciceController();
        str.charger(actionEvent,"addCategorieScreen.fxml");
    }

    /**
     * Cette méthode permet de retourner à l'écran précédent
     */
    public void retour(ActionEvent actionEvent) throws IOException {
        AddExerciceController str = new AddExerciceController();
        str.charger(actionEvent,"accueilScreen.fxml");
    }

    public void chargerPageExercice(ActionEvent actionEvent) throws IOException {
        AddExerciceController str = new AddExerciceController();
        str.charger(actionEvent,"addExerciceScreen.fxml");
    }
}
