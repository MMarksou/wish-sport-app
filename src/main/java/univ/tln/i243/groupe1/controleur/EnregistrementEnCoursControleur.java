package univ.tln.i243.groupe1.controleur;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import univ.tln.i243.groupe1.JME;
import univ.tln.i243.groupe1.daos.EnregistrementDao;
import univ.tln.i243.groupe1.entitees.Enregistrement;
import univ.tln.i243.groupe1.entitees.Frame;
import univ.tln.i243.groupe1.entitees.Jointure;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Classe de contrôle de la fenêtre "enregistrement en cours"
 */
public class EnregistrementEnCoursControleur  implements PageControleur, Initializable {

    @FXML
    private ProgressBar barreTemps;

    @FXML
    private Label labelInfoEnregistrement;

    private EntityManager em = Persistence.createEntityManagerFactory("bddlocal").createEntityManager();
    private EnregistrementDao enregistrementDao = new EnregistrementDao(em);

    /**
     * Confirme et persiste l'enregistrement courant
     * @param actionEvent action event
     * @throws IOException en cas d'erreur du fichier
     */
    public void validerEnregistrement(ActionEvent actionEvent) throws IOException {

        Enregistrement enregistrement = enregistrementDao.rechercherParNom(AjouterEnregistrementControleur.nomExo);

        for (Frame frame : ServeurJava.listeFrames) {

            enregistrement.ajouterFrame(frame);

            frame.setEnregistrement(enregistrement);

            for (Jointure jointure: frame.getJointures()) {
                jointure.setFrame(frame);
            }
        }

        enregistrementDao.supprimer(enregistrement);
        enregistrementDao.persister(enregistrement);

        ServeurJava.listeFrames.clear();

        chargerPage(actionEvent, "pageAccueil.fxml");
    }

    /**
     * Réinitialise la liste des frames et retour au formulaire
     * @param actionEvent action event
     * @throws IOException en cas d'erreur du fichier
     */
    public void retourEnregistrement(ActionEvent actionEvent) throws IOException {
        ServeurJava.listeFrames = new ArrayList<>();
        chargerPage(actionEvent, "pageAjouterEnregistrement.fxml");
    }

    /**
     * Visualise l'exercice courant dans jME
     * @param actionEvent action event
     */
    public void visualiserEnregistrement(ActionEvent actionEvent) {
        JME.main(ServeurJava.listeFrames);
    }

    /**
     * Déclenche le serveur java et le timer de l'exercice
     */
    @Override
    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ServeurJava serveurJava = new ServeurJava();

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

        Thread connexion = new Thread(() -> serveurJava.main(AjouterEnregistrementControleur.dureeExercice));

        connexion.start();
        progression.start();
    }
}
