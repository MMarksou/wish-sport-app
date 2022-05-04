package univ.tln.i243.groupe1.daos;

import univ.tln.i243.groupe1.entities.Jointure;

import javax.persistence.EntityManager;
import java.util.List;

public class JointureDao extends DAO<Jointure>{

    public static JointureDao of(EntityManager entityManager){
        return new JointureDao(entityManager);
    }

    public JointureDao(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    List<Jointure> findAll() {
        return entityManager.createNamedQuery("jointure.findAll",Jointure.class).getResultList();
    }

    List<Jointure> findByFrame(String frame) {
        return entityManager.createNamedQuery("jointure.findByFrame",Jointure.class).setParameter("frame",frame).getResultList();
    }
}
