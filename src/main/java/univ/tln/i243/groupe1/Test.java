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
        Categorie categorie = new Categorie();
        categorie.setNom("cat1");
        categorie.setDescription("deeeeescrtion");

        Enregistrement enregistrement = new Enregistrement();
        //enregistrement.setCategorie(categorie);
        enregistrement.setNom("enrg1");
        enregistrement.setDescription("random desc");
        enregistrement.setDuree(5);
        enregistrement.setRepetition(3);

        /*Frame frame = new Frame();
        frame.setEnregistrement(enregistrement);
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
        jointure.setZ(0.0F);*/

        EntityManager em = Persistence.createEntityManagerFactory("bddlocal").createEntityManager();

//        CategorieDao categorieDao = new CategorieDao(em);
//        categorieDao.persist(cat);
//
//        EnregistrementDao enregistrementDao = new EnregistrementDao(em);
//        enregistrementDao.persist(enregistrement);
//
//        FrameDao frameDao = new FrameDao(em);
//        frameDao.persist(frame);

        EnregistrementDao enre = new EnregistrementDao(em);
        enre.persist(enregistrement);


    }
}
