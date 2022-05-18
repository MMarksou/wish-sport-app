package univ.tln.i243.groupe1.controleur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import univ.tln.i243.groupe1.entitees.NomJointures;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AjouterAnglesRefsControleur implements PageControleur, Initializable {

    @FXML
    private ComboBox<String> comboJointure1;
    @FXML
    private ComboBox<String> comboJointure2;
    @FXML
    private ComboBox<String> comboJointure3;
    @FXML
    private Label labelMessage;


    /**
     * Fonction de validation et de persistance des angles de l'exercice de référence
     * @param actionEvent action event
     */
    public void validerExercice(ActionEvent actionEvent) {
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
    @Override
    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            for (NomJointures nomJointures : NomJointures.values()) {
                comboJointure1.getItems().add(nomJointures.name());
                comboJointure2.getItems().add(nomJointures.name());
                comboJointure3.getItems().add(nomJointures.name());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
