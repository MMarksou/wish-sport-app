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
    public Enregistrement rechercher(long id) {
        return em.find(Enregistrement.class,id);
    }

    @Override
    public Enregistrement rechercherParNom(String nom){
        Query query = em.createNamedQuery("Enregistrement.rechercherParNom");
        query.setParameter("nom",nom);
        return  (Enregistrement) query.getResultList().get(0);
    }

    @Override
    public List<Enregistrement> rechercherTout() {
        return em.createNamedQuery("Enregistrement.rechercherTout").getResultList();
    }
}
