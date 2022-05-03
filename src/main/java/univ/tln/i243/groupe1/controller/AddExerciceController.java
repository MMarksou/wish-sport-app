package univ.tln.i243.groupe1.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class AddExerciceController{
    /**
     * Cette méthode permet de charger la page de création d'une catégorie
     */
    public void charger(ActionEvent actionEvent, String str) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource(str)));
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void chargerPage(ActionEvent actionEvent) throws IOException {
        charger(actionEvent, "recordExercice.fxml");

    }

    /**
     * Cette méthode permet de retourner à l'écran précédent
     */
    public void retour(ActionEvent actionEvent, String str2) throws IOException {
        charger(actionEvent, "categorieScreen.fxml");
    }
}
