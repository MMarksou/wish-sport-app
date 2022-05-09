package univ.tln.i243.groupe1.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import univ.tln.i243.groupe1.daos.CategorieDao;
import univ.tln.i243.groupe1.entitees.Categorie;
import univ.tln.i243.groupe1.entitees.Enregistrement;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AjouterEnregistrementControleur implements PageControleur, Initializable {

    @FXML
    private ComboBox<String> comboCategorie;
    @FXML
    private Spinner<Integer> spinnerNbrRep;
    @FXML
    private Spinner<Integer> spinnerDuree;
    @FXML
    private TextField nomExercice;
    @FXML
    private TextArea descriptionExercice;
    @FXML
    private Label descriptionCategorie;

    protected static String nomCategorie;
    protected static int nbRepetition;
    protected static int dureeExercice;
    protected static String nomExo;
    protected static String descriptionExo;

    private CategorieDao categoriedao;

    private EntityManager em = Persistence.createEntityManagerFactory("bddlocal").createEntityManager();

    public void validerEnregistrement(ActionEvent actionEvent) throws IOException {

        Enregistrement enregistrement = new Enregistrement();

        Categorie categorie = categoriedao.rechercherParNom(comboCategorie.getValue());

        enregistrement.setCategorie(categorie);
        enregistrement.setRepetition(spinnerNbrRep.getValue());
        enregistrement.setDuree(spinnerDuree.getValue());
        enregistrement.setNom(nomExercice.getText());
        enregistrement.setDescription(descriptionExercice.getText());

        em.getTransaction().begin();
        em.persist(enregistrement);
        em.getTransaction().commit();

        miseAJourChamps(comboCategorie, spinnerNbrRep, spinnerDuree, nomExercice, descriptionExercice);

        chargerPage(actionEvent, "pageEnregistrement.fxml");
    }
    public static void miseAJourChamps(ComboBox<String> comboCategorie, Spinner<Integer> spinnerNbrRep, Spinner<Integer> spinnerDuree, TextField nomExercice, TextArea descriptionExercice){
        nomCategorie = comboCategorie.getValue();
        nbRepetition = spinnerNbrRep.getValue();
        dureeExercice = spinnerDuree.getValue();
        nomExo = nomExercice.getText();
        descriptionExo = descriptionExercice.getText();
    }
    public void retourChoix(ActionEvent actionEvent) throws IOException {
        chargerPage(actionEvent, "pageChoix.fxml");
    }
    public void afficherCategorieDescription(ActionEvent e){
        categoriedao = new CategorieDao(em);
        descriptionCategorie.setText(categoriedao.rechercherParNom(comboCategorie.getValue()).getDescription());
    }
    @Override
    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        categoriedao = new CategorieDao(em);
        List<Categorie> listeCategorie = categoriedao.rechercherTout();

        for(Categorie cat : listeCategorie ) {
            comboCategorie.getItems().add(cat.getNom());
        }

        SpinnerValueFactory<Integer> valueRep = new SpinnerValueFactory.IntegerSpinnerValueFactory(5, 100, 5, 5);
        SpinnerValueFactory<Integer> valueDuree = new SpinnerValueFactory.IntegerSpinnerValueFactory(10, 300, 10, 10);
        spinnerDuree.setValueFactory(valueDuree);
        spinnerNbrRep.setValueFactory(valueRep);
    }
}
