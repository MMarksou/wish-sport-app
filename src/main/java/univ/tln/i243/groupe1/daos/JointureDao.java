package univ.tln.i243.groupe1.daos;

import univ.tln.i243.groupe1.entitees.Jointure;

import javax.persistence.EntityManager;
import java.util.List;


public class JointureDao extends DAO<Jointure>{



    public JointureDao(EntityManager entityManager) {
        super(entityManager);
    }


    @Override
    public Jointure find(long id) {
        return null;
    }

    @Override
    public List<Jointure> findAll() {
        return null;
    }
}
