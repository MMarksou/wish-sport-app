package univ.tln.i243.groupe1.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import univ.tln.i243.groupe1.daos.CategorieDao;
import univ.tln.i243.groupe1.entitees.Categorie;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.io.IOException;

public class AjouterCategorieControleur implements PageControleur {

    @FXML
    private TextField nomCategorie;
    @FXML
    private TextArea descriptionCategorie;
    @FXML
    private Label labelValider;

    private EntityManager em = Persistence.createEntityManagerFactory("bddlocal").createEntityManager();

    public void validerCategorie(ActionEvent actionEvent){

        Categorie categorie = Categorie.builder().nom(nomCategorie.getText()).description(descriptionCategorie.getText()).build();
        CategorieDao categoriedao = new CategorieDao(em);
        categoriedao.persist(categorie);

        labelValider.setStyle("-fx-text-fill: green;\n");
        labelValider.setText("La catégorie a bien été enregistrée !");

        nomCategorie.setText("");
        descriptionCategorie.setText("");
    }

    public void retourChoix(ActionEvent actionEvent) throws IOException {
        chargerPage(actionEvent, "pageChoix.fxml");
    }
}
