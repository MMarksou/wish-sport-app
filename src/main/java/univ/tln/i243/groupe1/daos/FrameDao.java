package univ.tln.i243.groupe1.daos;
import univ.tln.i243.groupe1.entitees.Frame;

import javax.persistence.EntityManager;
import java.util.List;


public class FrameDao extends DAO<Frame>{



    public FrameDao(EntityManager entityManager) {
        super(entityManager);
    }


    @Override
    public Frame find(long id) {
        return null;
    }

    @Override
    public List<Frame> findAll() {
        return null;
    }
}
