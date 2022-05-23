package univ.tln.i243.groupe1.controleur;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import univ.tln.i243.groupe1.JME;
import univ.tln.i243.groupe1.daos.CategorieDao;
import univ.tln.i243.groupe1.daos.EnregistrementDao;
import univ.tln.i243.groupe1.entitees.Categorie;
import univ.tln.i243.groupe1.entitees.Enregistrement;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Classe contrôleur de visualisation, d'exportation et de suppression d'exercice
 */
public class ChoixEnregistrementControleur implements PageControleur, Initializable {

    @FXML
    private ComboBox<String> comboCategorie;
    @FXML
    private TableView<Enregistrement> tableEnregistrement;
    @FXML
    private TableColumn<Enregistrement, String> nomColumn;
    @FXML
    private TableColumn<Enregistrement, String> dureeColumn;
    @FXML
    private TableColumn<Enregistrement, String> repetitionColumn;
    @FXML
    private TableColumn<Enregistrement, String> descriptionColumn;


    private EntityManager em = Persistence.createEntityManagerFactory("bddlocal").createEntityManager();
    private CategorieDao categoriedao = new CategorieDao(em);
    private EnregistrementDao enregistrementdao = new EnregistrementDao(em);

    /**
     * Initialise la liste d'exercice selon la catégorie sélectionnée
     */
    @Override
    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            List<Categorie> listeCategorie = categoriedao.rechercherTout();

            for(Categorie cat : listeCategorie ) {
                comboCategorie.getItems().add(cat.getNom());
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Affiche la liste des enregistrements
     * @param data liste des enregistrements liés à la catégorie choisie
     */
    private void chargerEnsembleEnregistrement(ObservableList<Enregistrement> data) {
        try {
            nomColumn.setCellValueFactory(new PropertyValueFactory<>("Nom"));
            dureeColumn.setCellValueFactory(new PropertyValueFactory<>("Duree"));
            repetitionColumn.setCellValueFactory(new PropertyValueFactory<>("Repetition"));
            descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("Description"));

            tableEnregistrement.setItems(data);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Déclenche la visualisation de l'exercice sélectionné dans jME.
     * @param actionEvent action event
     */
    public void visualiserEnregistrement(ActionEvent actionEvent) {
        if (tableEnregistrement.getSelectionModel().getSelectedItem() != null) {
            JME.main(tableEnregistrement.getSelectionModel().getSelectedItem().getFrames());
        }
    }

    /**
     * Retour à la page d'acceuil
     * @param actionEvent action event
     * @throws IOException erreur fichier
     */
    public void retourEnregistrement(ActionEvent actionEvent) throws IOException {
        chargerPage(actionEvent,"pageAccueil.fxml");
    }

    /**
     * Supprime l'exercice sélectionné
     * @param actionEvent action event
     * @throws IOException erreur fichier
     */
    public void supprimerExercice(ActionEvent actionEvent) throws IOException {

        if (tableEnregistrement.getSelectionModel().getSelectedItem() != null) {
            Enregistrement enregistrement= tableEnregistrement.getSelectionModel().getSelectedItem();
            EnregistrementDao enregistrementDao = new EnregistrementDao(em);
            enregistrementDao.supprimer(enregistrement);
            chargerPage(actionEvent, "pageChoixEnregistrement.fxml");
        }

    }

    /**
     * Mise à jour de la liste d'exercice en cas de sélection de catégorie
     * @param actionEvent action event
     */
    public void chargerEnregistrements(ActionEvent actionEvent) {

        Categorie categorieActive = categoriedao.rechercherParNom(comboCategorie.getValue());

        ObservableList<Enregistrement> data = FXCollections.observableArrayList();

        data.addAll(enregistrementdao.rechercherParCategorie(categorieActive));

        chargerEnsembleEnregistrement(data);
    }

    /**
     * Fonction d'exportation d'un enregistrement en JSON
     * @param actionEvent action event
     * @throws IOException erreur sur le fichier
     */
    public void exporterEnregistrement(ActionEvent actionEvent) throws IOException {

        if (tableEnregistrement.getSelectionModel().getSelectedItem() != null) {

            Enregistrement enregistrement = tableEnregistrement.getSelectionModel().getSelectedItem();

            FileChooser choixFichier = new FileChooser();

            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json");
            choixFichier.getExtensionFilters().add(extFilter);

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            File fichier = choixFichier.showSaveDialog(stage);

            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(fichier, enregistrement.getFrames());
        }
    }
}
