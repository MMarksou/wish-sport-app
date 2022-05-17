package univ.tln.i243.groupe1.daos;
import univ.tln.i243.groupe1.entitees.Categorie;
import univ.tln.i243.groupe1.entitees.Enregistrement;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * DAO d'un enregistrement
 */
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
        if(query.getResultList().isEmpty()){
            return null;
        } else {
            return (Enregistrement) query.getResultList().get(0);
        }
    }

    @Override
    public List<Enregistrement> rechercherTout() {
        return em.createNamedQuery("Enregistrement.rechercherTout").getResultList();
    }

    public List<Enregistrement> rechercherParCategorie(Categorie categorie){
        Query query = em.createNamedQuery("Enregistrement.rechercherParCategorie");
        query.setParameter("categorie", categorie);
        return query.getResultList();
    }
}
