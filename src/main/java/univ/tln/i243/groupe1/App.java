package univ.tln.i243.groupe1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

/**
 * JavaFX App
 */
public class App extends Application {

    public Parent root;
    public void loadFichier(Stage stage){
        stage.initStyle(StageStyle.DECORATED);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @Override
    public void start(Stage stage) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("accueilScreen.fxml")));
        loadFichier(stage);

    }

    public static void main(String[] args) {
        launch();
    }

}