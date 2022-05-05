package univ.tln.i243.groupe1.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.w3c.dom.Text;
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

public class AjouterEnregistrementControleur implements PageControleur, Initializable {

    @FXML
    private ComboBox comboCategorie;
    @FXML
    private Spinner<Integer> spinnerNbrRep;
    @FXML
    private Spinner<Integer> spinnerDuree;
    @FXML
    private TextField nomExercice;
    @FXML
    private TextArea descriptionExercice;
    @FXML
    private Label catdesc;

    protected static String nomCategorie;
    protected static int nbRepetition;
    protected static int dureeExercice;
    protected static String nomExo;
    protected static String descriptionExo;

    private CategorieDao categoriedao;
    private EnregistrementDao enregistrementdao;


    private EntityManager em = Persistence.createEntityManagerFactory("bddlocal").createEntityManager();

    public void validerEnregistrement(ActionEvent actionEvent) throws IOException {

        Enregistrement enr = new Enregistrement();

        Categorie cat = categoriedao.findById(comboCategorie.getValue().toString());

        enr.setCategorie(cat);
        enr.setRepetition(spinnerNbrRep.getValue());
        enr.setDuree(spinnerDuree.getValue());
        enr.setNom(nomExercice.getText());
        enr.setDescription(descriptionExercice.getText());

        em.getTransaction().begin();
        em.persist(enr);
        em.getTransaction().commit();

        //enregistrementdao.persist(enr);

        nomCategorie = comboCategorie.getValue().toString();
        nbRepetition = spinnerNbrRep.getValue();
        dureeExercice = spinnerDuree.getValue();
        nomExo = nomExercice.getText();
        descriptionExo = descriptionExercice.getText();

        chargerPage(actionEvent, "pageEnregistrement.fxml");
    }
    public void retourChoix(ActionEvent actionEvent) throws IOException {
        chargerPage(actionEvent, "pageChoix.fxml");
    }
    public void showcatdesc(ActionEvent e){
        categoriedao = new CategorieDao(em);
        catdesc.setText(categoriedao.findById(comboCategorie.getValue().toString()).getDescription());
    }
    @Override
    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        categoriedao = new CategorieDao(em);
        List<Categorie> listeCategorie = categoriedao.findAll();

        for(Categorie cat : listeCategorie ) {
            comboCategorie.getItems().add(cat.getNom());
        }

        SpinnerValueFactory<Integer> valueRep = new SpinnerValueFactory.IntegerSpinnerValueFactory(5, 100, 5, 5);
        SpinnerValueFactory<Integer> valueDuree = new SpinnerValueFactory.IntegerSpinnerValueFactory(10, 300, 10, 10);
        spinnerDuree.setValueFactory(valueDuree);
        spinnerNbrRep.setValueFactory(valueRep);
    }
}
