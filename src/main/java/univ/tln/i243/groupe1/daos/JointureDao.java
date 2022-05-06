package univ.tln.i243.groupe1.daos;

import univ.tln.i243.groupe1.entitees.Jointure;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;


public class JointureDao extends DAO<Jointure>{



    public JointureDao(EntityManager entityManager) {
        super(entityManager);
    }


    @Override
    public Jointure find(long id) {
        return em.find(Jointure.class,id);
    }

    @Override
    public Jointure findByNom(String nom) {
        return null;
    }

    @Override
    public List<Jointure> findAll() {
        Query query = em.createNamedQuery("Jointure.findALl");
        List<Jointure> jointures = query.getResultList();

        return jointures;
    }
}
