package univ.tln.i243.groupe1.daos;
import univ.tln.i243.groupe1.entitees.Frame;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;


public class FrameDao extends DAO<Frame>{



    public FrameDao(EntityManager entityManager) {
        super(entityManager);
    }


    @Override
    public Frame find(long id) {
        return em.find(Frame.class,id);
    }

    @Override
    public List<Frame> findAll() {
        Query query = em.createNamedQuery("Frame.findALl");
        List<Frame> frames = query.getResultList();

        return frames;
    }
}
