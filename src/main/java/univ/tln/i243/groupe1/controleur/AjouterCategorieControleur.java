package univ.tln.i243.groupe1.controleur;

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

// TODO: 17/05/2022  
public class AjouterCategorieControleur implements PageControleur {

    @FXML
    private TextField nomCategorie;
    @FXML
    private TextArea descriptionCategorie;
    @FXML
    private Label labelValider;

    private EntityManager em = Persistence.createEntityManagerFactory("bddlocal").createEntityManager();

    CategorieDao categoriedao = new CategorieDao(em);

    public void validerCategorie (ActionEvent actionEvent) {

        if(!nomCategorie.getText().equals("")) {
            Categorie categorie = Categorie.builder().nom(nomCategorie.getText()).description(descriptionCategorie.getText()).build();

            if(categoriedao.rechercherParNom(categorie.getNom()) == null) {

                categoriedao.persister(categorie);

                labelValider.setStyle("-fx-text-fill: green;\n");
                labelValider.setText("La catégorie a bien été enregistrée !");

                nomCategorie.setText("");
                descriptionCategorie.setText("");
            } else {
                labelValider.setStyle("-fx-text-fill: red;\n");
                labelValider.setText("Une catégorie a déjà ce nom !");
            }
        } else {
            labelValider.setStyle("-fx-text-fill: red;\n");
            labelValider.setText("Le champ \"Catégorie\" est obligatoire !");
        }
    }

    public void retourChoix(ActionEvent actionEvent) throws IOException {
        chargerPage(actionEvent, "pageChoix.fxml");
    }
}
