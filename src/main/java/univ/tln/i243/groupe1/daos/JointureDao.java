package univ.tln.i243.groupe1.daos;

import univ.tln.i243.groupe1.entitees.Jointure;

import javax.persistence.EntityManager;
import java.util.List;


public class JointureDao extends DAO<Jointure>{

    public JointureDao(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Jointure rechercher(long id) {
        return em.find(Jointure.class,id);
    }

    @Override
    public Jointure rechercherParNom(String nom) {
        return null;
    }

    @Override
    public List<Jointure> rechercherTout() {
        return em.createNamedQuery("Jointure.rechercherTout").getResultList();
    }
}
