package univ.tln.i243.groupe1.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ProgressBar;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class EnregistrerControleur implements PageControleur{

    @FXML
    private ProgressBar temps;

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
        System.out.println(contenu);

        //Enregistrement enregistrement;
        //DAOEnregistrement daoEnregistrement = new DAOEnregistrement();

        ObjectMapper objectMapper = new ObjectMapper();

        //enregistrement.setFrame(objectMapper.readValue(contenu, new TypeReference<List<Frame>>() {}));
        //daoEnregistrement.persist()
    }
}
