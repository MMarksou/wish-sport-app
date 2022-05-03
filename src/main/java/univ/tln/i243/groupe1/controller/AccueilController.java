package univ.tln.i243.groupe1.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import univ.tln.i243.groupe1.controller.AddExerciceController.*;
public class AccueilController {

    public void chargerPage(ActionEvent actionEvent) throws IOException {
        // a Modifier le fichier XML
        //Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("categorieScreen.fxml")));
        AddExerciceController str = new AddExerciceController();
        str.charger(actionEvent,"categorieScreen.fxml");
    }

}
