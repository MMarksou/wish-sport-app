package univ.tln.i243.groupe1.controleur;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import univ.tln.i243.groupe1.JME;
import univ.tln.i243.groupe1.daos.EnregistrementDao;
import univ.tln.i243.groupe1.daos.FrameDao;
import univ.tln.i243.groupe1.entitees.Enregistrement;
import univ.tln.i243.groupe1.entitees.Frame;
import univ.tln.i243.groupe1.entitees.Jointure;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EnregistrementEnCoursControleur  implements PageControleur, Initializable {

    @FXML
    private ProgressBar barreTemps;

    @FXML
    private Label labelInfoEnregistrement;

    private EntityManager em = Persistence.createEntityManagerFactory("bddlocal").createEntityManager();
    private EnregistrementDao enregistrementDao = new EnregistrementDao(em);
    private FrameDao frameDao = new FrameDao(em);

    public void validerEnregistrement(ActionEvent actionEvent) throws IOException {

        Enregistrement enregistrement = enregistrementDao.rechercherParNom(AjouterEnregistrementControleur.nomExo);

        for (Frame frame : ServeurJava.listeFrames) {

            enregistrement.ajouterFrame(frame);

            frame.setEnregistrement(enregistrement);

            for (Jointure jointure: frame.getJointures()) {
                jointure.setFrame(frame);
            }

            frameDao.persister(frame);
        }

        enregistrementDao.recharger(enregistrement);

        ServeurJava.listeFrames.clear();

        chargerPage(actionEvent, "pageAccueil.fxml");
    }

    public void retourEnregistrement(ActionEvent actionEvent) throws IOException {
        ServeurJava.listeFrames = new ArrayList<>();
        chargerPage(actionEvent, "pageAjouterEnregistrement.fxml");
    }

    public void visualiserEnregistrement(ActionEvent actionEvent) {
        JME.main(ServeurJava.listeFrames);
    }

    @Override
    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Thread progression = new Thread(() -> {
            try {
                for (float i = 0; i <= AjouterEnregistrementControleur.dureeExercice; i++) {
                    barreTemps.setProgress(i/AjouterEnregistrementControleur.dureeExercice);
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

        Thread connexion = new Thread(() -> ServeurJava.main(AjouterEnregistrementControleur.dureeExercice, AjouterEnregistrementControleur.nomExo));

        connexion.start();
        progression.start();
    }
}
