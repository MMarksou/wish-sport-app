package univ.tln.i243.groupe1.controleur;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.MapValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import univ.tln.i243.groupe1.comparaison.VerifieAngle;
import univ.tln.i243.groupe1.daos.CategorieDao;
import univ.tln.i243.groupe1.daos.EnregistrementDao;
import univ.tln.i243.groupe1.daos.MouvementRefDao;
import univ.tln.i243.groupe1.entitees.*;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class AjouterAnglesRefsControleur implements PageControleur, Initializable {

    @FXML
    private ComboBox<String> comboCategorie;

    @FXML
    private ComboBox<String> comboJointure1;
    @FXML
    private ComboBox<String> comboJointure2;
    @FXML
    private ComboBox<String> comboJointure3;

    @FXML
    private TableColumn<Map, String> listeJointure1;
    @FXML
    private TableColumn<Map, String> listeJointure2;
    @FXML
    private TableColumn<Map, String> listeJointure3;

    @FXML
    private TableView tableEnregistrement;

    @FXML
    private Label labelMessage;

    @FXML
    private ProgressBar barreTemps;
    @FXML
    private Label labelInformationLancement;
    @FXML
    private Button boutonImporter;

    private ObservableList<Map<String, Object>> listeAngle = FXCollections.<Map<String, Object>>observableArrayList();
    private List<String> listeJointuresRefs = new ArrayList<>();

    private EntityManager em = Persistence.createEntityManagerFactory("bddlocal").createEntityManager();
    private CategorieDao categorieDao = new CategorieDao(em);
    private MouvementRefDao mouvementRefDao = new MouvementRefDao(em);


    public void importerMouvement(ActionEvent actionEvent) throws IOException {

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File fichier = fileChooser.showOpenDialog(stage);
        String contenu = Files.readString(Path.of(fichier.getPath()));

        EnregistrementDao enregistrementdao = new EnregistrementDao(em);
        Enregistrement enregistrement = enregistrementdao.rechercherParNom(AjouterEnregistrementControleur.nomExo);

        ObjectMapper objectMapper = new ObjectMapper();
        List<Frame> frames = objectMapper.readValue(contenu, new TypeReference<>() {
        });

        List<MouvementRef> listeMouvementsRefs = new ArrayList<>();

        Categorie categorie = categorieDao.rechercherParNom(comboCategorie.getValue());

        for(int i = 0; i < listeJointuresRefs.size(); i+=3){

            List<Double> listeAngles = VerifieAngle.calculerAngle(frames, 1, listeJointuresRefs.get(i), listeJointuresRefs.get(i+1), listeJointuresRefs.get(i+2));
            listeMouvementsRefs.add(MouvementRef.builder().angleDebut(listeAngles.get(0)).andgleFin(listeAngles.get(1)).jointure1(listeJointuresRefs.get(i)).jointure2(listeJointuresRefs.get(i+1)).jointure3(listeJointuresRefs.get(i+2)).categorie(categorie).build());
        }

        categorie.setMouvementsRefs(listeMouvementsRefs);

        afficherConfirmation(actionEvent, categorie);

    }

    /**
     * Fonction qui crée une boite de dialogue pour confirmer l'importation
     * @param actionEvent action event
     * @throws IOException en cas de problème d'ouverture de fichier
     */
    private void afficherConfirmation(ActionEvent actionEvent, Categorie categorie) throws IOException {

        Alert alerte = new Alert(Alert.AlertType.CONFIRMATION);
        alerte.setTitle("Importation");
        alerte.setHeaderText("Importation réussie !");

        ButtonType accueil = new ButtonType("Valider");
        ButtonType pageActuelle = new ButtonType("Annuler");

        alerte.getButtonTypes().clear();

        alerte.getButtonTypes().addAll(accueil, pageActuelle);

        Optional<ButtonType> option = alerte.showAndWait();

        if(option.isPresent()) { //choix des boutons
            if (option.get() == accueil) { //persiste et retour à l'accueil
                chargerPage(actionEvent, "pageAccueil.fxml");
                categorieDao.supprimer(categorie);
                categorieDao.persister(categorie);
            } else if (option.get() == pageActuelle) { //annulation des frames et retour au formulaire
               categorie = null;
            }
        }
    }

    public void enregistrerMouvement(ActionEvent actionEvent) {
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
                    chargerPage(actionEvent, "pageEnregistrementMouvementRef.fxml");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        });
        progression.start();
    }


    /**
     * Fonction de retour à l'accueil
     * @param actionEvent action event
     * @throws IOException si erreur sur les fichiers
     */
    public void retourAccueil(ActionEvent actionEvent) throws IOException {
        chargerPage(actionEvent,"pageAjouterExerciceReferent.fxml");
    }

    /**
     * Ajoute les jointures des Combobox dans la liste
     * @param actionEvent action event
     */
    public void ajouterJointures(ActionEvent actionEvent) {

        if(comboJointure1.getValue() != null &&  comboJointure2.getValue() != null &&  comboJointure3.getValue() != null) {
            if (!comboJointure1.getValue().equals(comboJointure2.getValue()) && !comboJointure1.getValue().equals(comboJointure3.getValue()) && !comboJointure3.getValue().equals(comboJointure2.getValue())) {
                labelMessage.setText("");

                listeJointure1.setCellValueFactory(new MapValueFactory<>("Jointure1"));
                listeJointure2.setCellValueFactory(new MapValueFactory<>("Jointure2"));
                listeJointure3.setCellValueFactory(new MapValueFactory<>("Jointure3"));

                Map<String, Object> angle = new HashMap<>();
                angle.put("Jointure1", comboJointure1.getValue());
                angle.put("Jointure2", comboJointure2.getValue());
                angle.put("Jointure3", comboJointure3.getValue());

                listeJointuresRefs.add(comboJointure1.getValue());
                listeJointuresRefs.add(comboJointure2.getValue());
                listeJointuresRefs.add(comboJointure3.getValue());

                listeAngle.add(angle);

                tableEnregistrement.getItems().addAll(angle);


            } else {
                labelMessage.setText("Veuillez ne pas choisir les mêmes jointures !");
            }
        } else {
            labelMessage.setText("Veuillez selectionner toutes les jointures !");
        }
    }

    /**
     * Redéfinit la fonction pour remplir les Combobox en fonction des noms de jointures disponibles
     */
    public void chargerJointure(ActionEvent actionEvent) {

        comboJointure1.setDisable(false);
        comboJointure2.setDisable(false);
        comboJointure3.setDisable(false);

        for (NomJointures nomJointures : NomJointures.values()) {
            comboJointure1.getItems().add(nomJointures.name());
            comboJointure2.getItems().add(nomJointures.name());
            comboJointure3.getItems().add(nomJointures.name());
        }
    }


    @Override
    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            List<Categorie> listeCategorie = categorieDao.rechercherTout();

            for(Categorie cat : listeCategorie ) {
                comboCategorie.getItems().add(cat.getNom());
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }



}
