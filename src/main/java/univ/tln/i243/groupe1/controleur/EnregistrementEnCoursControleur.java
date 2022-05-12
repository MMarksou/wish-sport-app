package univ.tln.i243.groupe1.controleur;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EnregistrementEnCoursControleur  implements PageControleur, Initializable {

    @FXML
    private ProgressBar barreTemps;

    @FXML
    private Label labelInfoEnregistrement;


    public void validerEnregistrement(ActionEvent actionEvent) {
    }

    public void retourEnregistrement(ActionEvent actionEvent) throws IOException {
        chargerPage(actionEvent, "pageAjouterEnregistrement.fxml");
    }

    public void visualiserEnregistrement(ActionEvent actionEvent) {
    }

    @Override
    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Thread progression = new Thread(new Runnable() {
            @Override public void run() {
                float countdownSeconds = (float)(AjouterEnregistrementControleur.dureeExercice);
                for (float i = 0; i <= 1; i+= (float) 1/AjouterEnregistrementControleur.dureeExercice) {
                    try {
                        barreTemps.setProgress(i);
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                Platform.runLater(() -> {
                    labelInfoEnregistrement.setText("L'enregistrement est terminé ! ");
                    labelInfoEnregistrement.setStyle("-fx-background-color: cyan");
                });
            }
        });
        progression.start();
    }
}
