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
 * Cette classe représente l'application JavaFX
 */
public class App extends Application {

    @Override
    /**
     * Cette méthode charge la page d'accueil dans la scène
     */
    public void start(Stage stage) throws IOException {

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("pageAccueil.fxml")));
        stage.initStyle(StageStyle.DECORATED);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Wish Sport");
        stage.show();
    }

    /**
     * Cette méthode démarre l'application sur la scène
     * @param args
     */
    public static void main(String[] args) {
        launch();
    }

}
