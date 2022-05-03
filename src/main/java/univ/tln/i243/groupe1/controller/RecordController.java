package univ.tln.i243.groupe1.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class RecordController {

    public void chargerPage(ActionEvent actionEvent) throws IOException {
        // a Modifier le fichier XML
        AddExerciceController str = new AddExerciceController();
        str.charger(actionEvent,"categorieScreen.fxml");
    }
    /**
     * Cette méthode permet de retourner à l'écran précédent
     */
    public void retour(ActionEvent actionEvent) throws IOException {
        AddExerciceController str = new AddExerciceController();
        str.charger(actionEvent,"addExerciceScreen.fxml");
    }
}
