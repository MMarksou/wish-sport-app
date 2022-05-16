package univ.tln.i243.groupe1.controleur;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import univ.tln.i243.groupe1.daos.EnregistrementDao;
import univ.tln.i243.groupe1.daos.FrameDao;
import univ.tln.i243.groupe1.entitees.Enregistrement;
import univ.tln.i243.groupe1.entitees.Frame;
import univ.tln.i243.groupe1.entitees.Jointure;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class EnregistrerControleur implements PageControleur, Initializable {

    @FXML
    private ProgressBar barreTemps;

    @FXML
    private Label nomCategorie;

    @FXML
    private Label nbRepetition;

    @FXML
    private Label dureeType;

    @FXML
    private Label nomExercice;

    @FXML
    private TextArea descriptionExercice;

    @FXML
    private Button boutonImporter;

    @FXML
    private Label labelInformationLancement;


    private EntityManager em = Persistence.createEntityManagerFactory("bddlocal").createEntityManager();
    private EnregistrementDao enregistrementDao = new EnregistrementDao(em);
    private FrameDao frameDao = new FrameDao(em);


    public void retourEnregistrement(ActionEvent actionEvent) throws IOException {
        enregistrementDao.supprimer(enregistrementDao.rechercherParNom(nomExercice.getText()));
        chargerPage(actionEvent, "pageAjouterEnregistrement.fxml");
    }

    public void lancerEnregistrement(ActionEvent actionEvent) {

        boutonImporter.setVisible(false);
        labelInformationLancement.setVisible(true);
        labelInformationLancement.setText("Placez-vous devant la Kinect");


        Thread progression = new Thread(() -> {
            for (float i = 0; i <= 1; i+=(float) 1/5) {
                try {
                    barreTemps.setProgress(i);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            Platform.runLater(() -> {
                try {
                    chargerPage(actionEvent, "pageEnregistrement.fxml");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        });
        progression.start();
    }


    public void importerEnregistrement(ActionEvent actionEvent) throws IOException {

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File fichier = fileChooser.showOpenDialog(stage);
        String contenu = Files.readString(Path.of(fichier.getPath()));

        EnregistrementDao enregistrementdao = new EnregistrementDao(em);
        Enregistrement enregistrement = enregistrementdao.rechercherParNom(nomExercice.getText());

        ObjectMapper objectMapper = new ObjectMapper();
        List<Frame> frames = objectMapper.readValue(contenu, new TypeReference<>() {
        });

        for (Frame frame : frames) {
            frame.setEnregistrement(enregistrement);

            for (Jointure jointure : frame.getJointures()) {
                jointure.setFrame(frame);
            }
        }

        enregistrement.setFrames(frames);
        enregistrementdao.persister(enregistrement);

        afficherConfirmation(actionEvent);

    }

    private void afficherConfirmation(ActionEvent actionEvent) throws IOException {

        Alert alerte = new Alert(Alert.AlertType.CONFIRMATION);
        alerte.setTitle("Importation");
        alerte.setHeaderText("Importation réussie !");

        ButtonType accueil = new ButtonType("Valider");
        ButtonType pageActuelle = new ButtonType("Annuler");

        alerte.getButtonTypes().clear();

        alerte.getButtonTypes().addAll(accueil, pageActuelle);

        Optional<ButtonType> option = alerte.showAndWait();

        if(option.isPresent()) {
            if (option.get() == accueil) {
                chargerPage(actionEvent, "pageAccueil.fxml");
            } else if (option.get() == pageActuelle) {
                Enregistrement enregistrement = enregistrementDao.rechercherParNom(nomExercice.getText());
                List<Frame> listeFrames = enregistrement.getFrames();
                enregistrement.setFrames(null);
                for (Frame frame : listeFrames) {
                    frameDao.supprimer(frame);
                }
            }
        }
    }

    @Override
    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nomCategorie.setText(AjouterEnregistrementControleur.nomCategorie);
        nbRepetition.setText(String.valueOf(AjouterEnregistrementControleur.nbRepetition));
        dureeType.setText(String.valueOf(AjouterEnregistrementControleur.dureeExercice));
        nomExercice.setText(AjouterEnregistrementControleur.nomExo);
        descriptionExercice.setText(AjouterEnregistrementControleur.descriptionExo);
    }
}
