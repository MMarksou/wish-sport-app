package univ.tln.i243.groupe1.daos;
import univ.tln.i243.groupe1.entitees.Enregistrement;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class EnregistrementDao extends DAO<Enregistrement>{

    public EnregistrementDao(EntityManager entityManager) {
        super(entityManager);
    }


    @Override
    public Enregistrement find(long id) {
        return em.find(Enregistrement.class,id);
    }

    @Override
    public List<Enregistrement> findAll() {
        Query query = em.createNamedQuery("Enregistrement.findALl");
        List<Enregistrement> enregistrements = query.getResultList();

        return enregistrements;
    }
}
