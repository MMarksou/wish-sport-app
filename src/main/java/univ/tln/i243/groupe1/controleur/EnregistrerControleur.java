package univ.tln.i243.groupe1.controleur;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import univ.tln.i243.groupe1.daos.EnregistrementDao;
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
import java.util.ResourceBundle;

public class EnregistrerControleur implements PageControleur, Initializable {

    @FXML
    private ProgressBar temps;

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

    private EntityManager em = Persistence.createEntityManagerFactory("bddlocal").createEntityManager();

    public void retourEnregistrement(ActionEvent actionEvent) throws IOException {
        chargerPage(actionEvent, "pageAjouterEnregistrement.fxml");
    }

    public void lancerEnregistrement(ActionEvent actionEvent) {

        Thread thread = new Thread(new Runnable () {
            @Override
            public void run() {
                float countdownSeconds = 1f;
                for (float i = 0; i <= countdownSeconds; i = i + 0.01f) {
                    try {
                        temps.setProgress(i);
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                    }
                }
            }
        });
        thread.start();
    }

    public void importerEnregistrement(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File fichier = fileChooser.showOpenDialog(stage);
        String contenu = Files.readString(Path.of(fichier.getPath()));

        EnregistrementDao enregistrementdao = new EnregistrementDao(em);
        Enregistrement enregistrement = enregistrementdao.rechercherParNom(nomExercice.getText());

        ObjectMapper objectMapper = new ObjectMapper();
        List<Frame> frames = objectMapper.readValue(contenu, new TypeReference<List<Frame>>() {});

        for (Frame frame: frames) {
            frame.setEnregistrement(enregistrement);

            for (Jointure jointure: frame.getJointures()) {
                jointure.setFrame(frame);
            }
        }

        enregistrement.setFrames(frames);
        enregistrementdao.persister(enregistrement);
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
