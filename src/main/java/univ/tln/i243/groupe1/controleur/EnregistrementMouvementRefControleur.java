package univ.tln.i243.groupe1.controleur;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import univ.tln.i243.groupe1.JME;
import univ.tln.i243.groupe1.comparaison.VerifieAngle;
import univ.tln.i243.groupe1.daos.CategorieDao;
import univ.tln.i243.groupe1.entitees.*;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Classe de contrôle de la fenêtre d'enregistrement d'un mouvement de référence
 */
public class EnregistrementMouvementRefControleur  implements PageControleur, Initializable {

    @FXML
    private ProgressBar barreTemps;

    @FXML
    private Label labelInfoEnregistrement;

    private EntityManager em = Persistence.createEntityManagerFactory("bddlocal").createEntityManager();
    private CategorieDao categorieDao = new CategorieDao(em);

    /**
     * Confirme et persiste l'enregistrement courant
     * @param actionEvent action event
     * @throws IOException en cas d'erreur du fichier
     */
    public void validerEnregistrement(ActionEvent actionEvent) throws IOException {
        Categorie categorie = categorieDao.rechercherParNom(AjouterAnglesRefsControleur.nomCategorie);

        List<MouvementRef> listeMouvementsRefs = new ArrayList<>();

        for(int i = 0; i < AjouterAnglesRefsControleur.listeJointuresRefs.size(); i+=3){

            List<Double> listeAngles = VerifieAngle.calculerAngle(ServeurJava.listeFrames, 1, AjouterAnglesRefsControleur.listeJointuresRefs.get(i), AjouterAnglesRefsControleur.listeJointuresRefs.get(i+1), AjouterAnglesRefsControleur.listeJointuresRefs.get(i+2));
            listeMouvementsRefs.add(MouvementRef.builder().angleDebut(listeAngles.get(0)).andgleFin(listeAngles.get(1)).jointure1(AjouterAnglesRefsControleur.listeJointuresRefs.get(i)).jointure2(AjouterAnglesRefsControleur.listeJointuresRefs.get(i+1)).jointure3(AjouterAnglesRefsControleur.listeJointuresRefs.get(i+2)).categorie(categorie).build());
        }

        categorie.setMouvementsRefs(listeMouvementsRefs);

        categorieDao.supprimer(categorie);
        categorieDao.persister(categorie);

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

        Thread connexion = new Thread(() -> serveurJava.main(5));

        connexion.start();
        progression.start();
    }
}
