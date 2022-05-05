package univ.tln.i243.groupe1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import univ.tln.i243.groupe1.daos.JointureDao;
import univ.tln.i243.groupe1.entities.Categorie;
import univ.tln.i243.groupe1.entities.Enregistrement;
import univ.tln.i243.groupe1.entities.Frame;
import univ.tln.i243.groupe1.entities.Jointure;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.io.IOException;
import java.util.Objects;

/**
 * JavaFX App
 */
public class App extends Application {

    public Parent root;

    public void loadFichier(Stage stage) {
        stage.initStyle(StageStyle.DECORATED);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void start(Stage stage) throws IOException {
        EntityManagerFactory emf = Persistence
                .createEntityManagerFactory("bdd");
        EntityManager em = emf.createEntityManager();

        Categorie categorie = new Categorie();
        categorie.setNom("cat1");
        categorie.setDescription("deeeeescrtion");

        Enregistrement enregistrement = new Enregistrement();
        enregistrement.setCat(categorie);
        enregistrement.setNom("enrg1");
        enregistrement.setDescription("random desc");
        enregistrement.setDuree(5);
        enregistrement.setRepetition(3);

        Frame frame = new Frame();
        frame.setEnreg(enregistrement);
        frame.setNumero(1);

        Jointure jointure = new Jointure();
        jointure.setId("head");
        jointure.setNom("head");
        jointure.setFrame(frame);
        jointure.setW(0.0F);
        jointure.setWx(0.0F);
        jointure.setWy(0.0F);
        jointure.setWz(0.0F);
        jointure.setX(0.0F);
        jointure.setY(0.0F);
        jointure.setZ(0.0F);

        System.out.println("wooorking !!!");

        EntityTransaction transac = em.getTransaction();
        transac.begin();
        em.persist(categorie);
        em.persist(enregistrement);
        em.persist(frame);
        em.persist(jointure);
        transac.commit();

        root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("accueilScreen.fxml")));
        loadFichier(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}