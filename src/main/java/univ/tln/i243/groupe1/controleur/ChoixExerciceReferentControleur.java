package univ.tln.i243.groupe1.controleur;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import univ.tln.i243.groupe1.daos.CategorieDao;
import univ.tln.i243.groupe1.daos.EnregistrementDao;
import univ.tln.i243.groupe1.entitees.Categorie;
import univ.tln.i243.groupe1.entitees.Enregistrement;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ChoixExerciceReferentControleur implements PageControleur, Initializable {


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

    protected static Enregistrement exerciceReferent;

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
     * Charge la page de création des angles de l'exercice référent choisi.
     * @param actionEvent action event
     */
    public void validerExercice(ActionEvent actionEvent) throws IOException {
        if (tableEnregistrement.getSelectionModel().getSelectedItem() != null) {
            exerciceReferent =  tableEnregistrement.getSelectionModel().getSelectedItem();
            chargerPage(actionEvent, "pageAjouterAnglesRefs.fxml");
        }
    }

    /**
     * Retour à la page d'acceuil
     * @param actionEvent action event
     * @throws IOException erreur fichier
     */
    public void retourAccueil(ActionEvent actionEvent) throws IOException {
        chargerPage(actionEvent,"pageAccueil.fxml");
    }

    /**
     * Mise à jour de la liste d'exercice en cas de sélection de catégorie
     * @param actionEvent action event
     */
    public void chargerEnregistrements(ActionEvent actionEvent) {

        Categorie categorieActive = categoriedao.rechercherParNom(comboCategorie.getValue());

        ObservableList<Enregistrement> data = FXCollections.observableArrayList();

        data.addAll( enregistrementdao.rechercherParCategorie(categorieActive));

        chargerEnsembleEnregistrement(data);
    }
}
