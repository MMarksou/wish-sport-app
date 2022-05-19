package univ.tln.i243.groupe1.daos;

import univ.tln.i243.groupe1.entitees.Categorie;
import univ.tln.i243.groupe1.entitees.MouvementRef;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * DAO d'un mouvement reference
 */
public class MouvementRefDao extends DAO<MouvementRef>{

    public MouvementRefDao(EntityManager em) {
        super(em);
    }

    @Override
    public MouvementRef rechercher(long id) {
        return em.find(MouvementRef.class,id);
    }

    @Override
    public MouvementRef rechercherParNom(String nom) {
        return null;
    }

    public MouvementRef rechercherParCategorie(Categorie categorie){
        Query query = em.createNamedQuery("MouvementRef.rechercherParCategorie");
        query.setParameter("categorie",categorie);
        if(query.getResultList().isEmpty()){
            return null;
        } else {
            return (MouvementRef) query.getResultList().get(0);
        }
    }

    @Override
    public List<MouvementRef> rechercherTout() {
        return em.createNamedQuery("MouvementRef.rechercherTout").getResultList();
    }
}
