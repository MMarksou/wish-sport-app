package univ.tln.i243.groupe1.controleur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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

/**
 * Classe controleur du formulaire de création d'un exercice.
 */
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
    @FXML
    private Label labelValider;
    @FXML
    private Button boutonValider;

    protected static String nomCategorie;
    protected static int nbRepetition;
    protected static int dureeExercice;
    protected static String nomExo;
    protected static String descriptionExo;

    private EntityManager em = Persistence.createEntityManagerFactory("bddlocal").createEntityManager();

    private CategorieDao categoriedao = new CategorieDao(em);
    private EnregistrementDao enregistrementDao = new EnregistrementDao(em);

    /**
     * Gestion de la validité d'un exercice
     * @param actionEvent action event
     * @throws IOException erreur fichier
     */
    public void validerEnregistrement(ActionEvent actionEvent) throws IOException {

        if(!nomExercice.getText().equals("")) {

            Enregistrement enregistrement = new Enregistrement();

            Categorie categorie = categoriedao.rechercherParNom(comboCategorie.getValue());

            enregistrement.setCategorie(categorie);
            enregistrement.setRepetition(spinnerNbrRep.getValue());
            enregistrement.setDuree(spinnerDuree.getValue());
            enregistrement.setNom(nomExercice.getText());
            enregistrement.setDescription(descriptionExercice.getText());

            if(enregistrementDao.rechercherParNom(enregistrement.getNom()) == null) {

                enregistrementDao.persister(enregistrement);

                miseAJourChamps(comboCategorie, spinnerNbrRep, spinnerDuree, nomExercice, descriptionExercice);

                chargerPage(actionEvent, "pageResumeEnregistrement.fxml");
            } else {
                labelValider.setStyle("-fx-text-fill: red;\n");
                labelValider.setText("Ce nom d'exercice existe déjà !");
            }
        } else {
            labelValider.setStyle("-fx-text-fill: red;\n");
            labelValider.setText("Le nom de l'exercice est obligatoire !");
        }
    }

    /**
     * Mise à jour des champs static pour l'accès des données
     */
    public static void miseAJourChamps(ComboBox<String> comboCategorie, Spinner<Integer> spinnerNbrRep, Spinner<Integer> spinnerDuree, TextField nomExercice, TextArea descriptionExercice){
        nomCategorie = comboCategorie.getValue();
        nbRepetition = spinnerNbrRep.getValue();
        dureeExercice = spinnerDuree.getValue();
        nomExo = nomExercice.getText();
        descriptionExo = descriptionExercice.getText();
    }

    /**
     * Retour à la page précédente
     * @param actionEvent action event
     * @throws IOException erreur fichier
     */
    public void retourChoix(ActionEvent actionEvent) throws IOException {
        chargerPage(actionEvent, "pageChoix.fxml");
    }

    /**
     * Affiche la description d'une catégorie et débloque le bouton valider
     * @param e ae
     */
    public void afficherCategorieDescription(ActionEvent e){
        categoriedao = new CategorieDao(em);
        descriptionCategorie.setText(categoriedao.rechercherParNom(comboCategorie.getValue()).getDescription());
        boutonValider.setDisable(false);
    }

    /**
     * Initialise les widgets avec les plages voulues
     */
    @Override
    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        categoriedao = new CategorieDao(em);
        List<Categorie> listeCategorie = categoriedao.rechercherTout();

        for(Categorie cat : listeCategorie ) {
            comboCategorie.getItems().add(cat.getNom());
        }

        SpinnerValueFactory<Integer> valueRep = new SpinnerValueFactory.IntegerSpinnerValueFactory(5, 100, 5, 5);
        SpinnerValueFactory<Integer> valueDuree = new SpinnerValueFactory.IntegerSpinnerValueFactory(5, 300, 5, 5);
        spinnerDuree.setValueFactory(valueDuree);
        spinnerNbrRep.setValueFactory(valueRep);
    }
}
