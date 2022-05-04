package univ.tln.i243.groupe1.daos;
import univ.tln.i243.groupe1.entities.Frame;

import javax.persistence.EntityManager;
import java.util.List;

public class FrameDao extends DAO<Frame>{

    public static FrameDao of(EntityManager entityManager){
        return new FrameDao(entityManager);
    }

    public FrameDao(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    List<Frame> findAll() {
        return entityManager.createNamedQuery("frame.findAll",Frame.class).getResultList();
    }

    List<Frame> findByEnregistrement(String enreg) {
        return entityManager.createNamedQuery("frame.findByEnregistrement",Frame.class).setParameter("enreg",enreg).getResultList();
    }
}
