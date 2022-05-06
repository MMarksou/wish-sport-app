package univ.tln.i243.groupe1;

import univ.tln.i243.groupe1.daos.EnregistrementDao;
import univ.tln.i243.groupe1.entitees.Categorie;
import univ.tln.i243.groupe1.entitees.Enregistrement;
import univ.tln.i243.groupe1.entitees.Frame;
import univ.tln.i243.groupe1.entitees.Jointure;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        Categorie cat = Categorie.builder().description("11").nom("22").build();
        Enregistrement enr = Enregistrement.builder().nom("en1").build();
        Frame frame = Frame.builder().numero(1).build();
        Jointure jointure = Jointure.builder().nom("heasd").build();



        List<Jointure> lj = new ArrayList<>();
        lj.add(jointure);
        enr.setCategorie(cat);
        frame.setJointures(lj);

        List<Frame> lf = new ArrayList<>();
        lf.add(frame);

        enr.setFrames(lf);




        EntityManager em = Persistence.createEntityManagerFactory("bddlocal").createEntityManager();

        EnregistrementDao enregistrementDao = new EnregistrementDao(em);
        enregistrementDao.persister(enr);
    }
}
