package univ.tln.i243.groupe1.controleur;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import univ.tln.i243.groupe1.JME;
import univ.tln.i243.groupe1.daos.EnregistrementDao;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EnregistrementEnCoursControleur  implements PageControleur, Initializable {

    @FXML
    private ProgressBar barreTemps;

    @FXML
    private Label labelInfoEnregistrement;

    private EntityManager em = Persistence.createEntityManagerFactory("bddlocal").createEntityManager();
    private EnregistrementDao enregistrementDao = new EnregistrementDao(em);

    public void validerEnregistrement(ActionEvent actionEvent) throws IOException {
        chargerPage(actionEvent, "pageAccueil.fxml");
    }

    public void retourEnregistrement(ActionEvent actionEvent) throws IOException {
        enregistrementDao.supprimer(enregistrementDao.rechercherParNom(AjouterEnregistrementControleur.nomExo));
        chargerPage(actionEvent, "pageAjouterEnregistrement.fxml");
    }

    public void visualiserEnregistrement(ActionEvent actionEvent) {
        JME.main(enregistrementDao.rechercherParNom(AjouterEnregistrementControleur.nomExo).getId());
    }

    @Override
    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Thread progression = new Thread(() -> {
            try {
                for (float i = 0; i <= 1; i+= (float) 1/AjouterEnregistrementControleur.dureeExercice) {
                    barreTemps.setProgress(i);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            Platform.runLater(() -> {
                labelInfoEnregistrement.setText("L'enregistrement est terminé ! ");
                labelInfoEnregistrement.setStyle("-fx-background-color: green");
            });
        });
        progression.start();

        Thread connexion = new Thread(() -> ServeurJava.main(AjouterEnregistrementControleur.dureeExercice, AjouterEnregistrementControleur.nomExo));
        connexion.start();
    }
}
