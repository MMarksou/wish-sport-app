package univ.tln.i243.groupe1.controleur;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import univ.tln.i243.groupe1.JME;
import univ.tln.i243.groupe1.daos.EnregistrementDao;
import univ.tln.i243.groupe1.entitees.Enregistrement;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class ChoixEnregistrementControleur implements PageControleur, Initializable {

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


    @Override
    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ObservableList<Enregistrement> data = FXCollections.observableArrayList(new EnregistrementDao(em).rechercherTout());
            chargerEnsembleEnregistrement(data);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void chargerEnsembleEnregistrement(ObservableList<Enregistrement> data) {
        try {
            //chargement de la table des enseignants au niveau de l'interface apres la demande de la liste des enseignants
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
            JME.main(null,enregistrement.getId());
        }
    }

    public void retourEnregistrement(ActionEvent actionEvent) throws IOException {
        chargerPage(actionEvent,"pageAccueil.fxml");
    }
}
