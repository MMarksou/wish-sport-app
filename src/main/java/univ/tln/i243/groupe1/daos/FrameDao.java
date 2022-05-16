package univ.tln.i243.groupe1.daos;
import univ.tln.i243.groupe1.entitees.Categorie;
import univ.tln.i243.groupe1.entitees.Enregistrement;
import univ.tln.i243.groupe1.entitees.Frame;

import javax.persistence.EntityManager;
import javax.persistence.Query;
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

    public List<Frame> rechercherParEnregistrement(Enregistrement enregistrement){
        Query query = em.createNamedQuery("Frame.rechercherParEnregistrement");
        query.setParameter("enregistrement", enregistrement);
        return query.getResultList();
    }
}
