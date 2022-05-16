package univ.tln.i243.groupe1.controleur;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import univ.tln.i243.groupe1.JME;
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

    public void visualiserEnregistrement(ActionEvent actionEvent) {
        if (tableEnregistrement.getSelectionModel().getSelectedItem() != null) {
             Enregistrement enregistrement= tableEnregistrement.getSelectionModel().getSelectedItem();
            JME.main(enregistrement.getId());
        }
    }

    public void retourEnregistrement(ActionEvent actionEvent) throws IOException {
        chargerPage(actionEvent,"pageAccueil.fxml");
    }

    public void supprimerExercice(ActionEvent actionEvent) throws IOException {

        if (tableEnregistrement.getSelectionModel().getSelectedItem() != null) {
            Enregistrement enregistrement= tableEnregistrement.getSelectionModel().getSelectedItem();
            EnregistrementDao enregistrementDao = new EnregistrementDao(em);
            enregistrementDao.supprimer(enregistrement);
            chargerPage(actionEvent, "pageChoixEnregistrement.fxml");
        }

    }

    public void chargerEnregistrements(ActionEvent actionEvent) {

        Categorie categorieActive = categoriedao.rechercherParNom(comboCategorie.getValue());

        ObservableList<Enregistrement> data = FXCollections.observableArrayList();

        for (Enregistrement enregistrement: enregistrementdao.rechercherParCategorie(categorieActive)) {
            data.add(enregistrement);
        }

        chargerEnsembleEnregistrement(data);
    }
}
