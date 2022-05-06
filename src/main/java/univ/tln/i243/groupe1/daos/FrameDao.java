package univ.tln.i243.groupe1.daos;
import univ.tln.i243.groupe1.entitees.Frame;

import javax.persistence.EntityManager;
import java.util.List;


public class FrameDao extends DAO<Frame>{

    public FrameDao(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Frame rechercher(long id) {
        return em.find(Frame.class,id);
    }

    @Override
    public Frame rechercherParNom(String nom) {
        return null;
    }

    @Override
    public List<Frame> rechercherTout() {
       return em.createNamedQuery("Frame.rechercherTout").getResultList();
    }
}
