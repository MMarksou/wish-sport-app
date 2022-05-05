package univ.tln.i243.groupe1.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AjouterCategorieControleur implements PageControleur {

    @FXML
    private TextField nomCategorie;
    @FXML
    private TextArea descriptionCategorie;

    public void validerCategorie(ActionEvent actionEvent){

        //Categorie categorie = new Categorie(nomCategrorie.getText(), descriptionCategorie.getText());
        //DAOCategorie daocategorie = new DAOCategorie;
        //daoCategorie.persist(categorie);
    }

    public void retourChoix(ActionEvent actionEvent) throws IOException {
        chargerPage(actionEvent, "pageChoix.fxml");
    }
}
