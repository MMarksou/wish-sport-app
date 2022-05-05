package univ.tln.i243.groupe1;

import univ.tln.i243.groupe1.daos.CategorieDao;
import univ.tln.i243.groupe1.daos.EnregistrementDao;
import univ.tln.i243.groupe1.daos.FrameDao;
import univ.tln.i243.groupe1.daos.JointureDao;
import univ.tln.i243.groupe1.entitees.Categorie;
import univ.tln.i243.groupe1.entitees.Enregistrement;
import univ.tln.i243.groupe1.entitees.Frame;
import univ.tln.i243.groupe1.entitees.Jointure;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class Test {
    public static void main(String[] args) {
        Categorie cat = Categorie.builder().nom("Squat").build();
        Enregistrement enregistrement = Enregistrement.builder().nom("NathanSquat").durée(10).categorie(cat).repetition(2).build();
        Frame frame = Frame.builder().numero(1).enregistrement(enregistrement).build();
        Jointure jointure = Jointure.builder().nom("HOLLA").frame(frame).build();

        EntityManager em = Persistence.createEntityManagerFactory("bddlocal").createEntityManager();

//        CategorieDao categorieDao = new CategorieDao(em);
//        categorieDao.persist(cat);
//
//        EnregistrementDao enregistrementDao = new EnregistrementDao(em);
//        enregistrementDao.persist(enregistrement);
//
//        FrameDao frameDao = new FrameDao(em);
//        frameDao.persist(frame);

        JointureDao jointureDao = new JointureDao(em);
        jointureDao.persist(jointure);


    }
}
