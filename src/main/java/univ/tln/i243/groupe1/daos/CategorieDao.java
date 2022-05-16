package univ.tln.i243.groupe1.daos;

import univ.tln.i243.groupe1.entitees.Categorie;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class CategorieDao extends DAO<Categorie>{

    public CategorieDao(EntityManager em) {
        super(em);
    }


    @Override
    public Categorie rechercherParNom(String nom){
        Query query = em.createNamedQuery("Categorie.rechercherParNom");
        query.setParameter("nom",nom);
        if(query.getResultList().isEmpty()){
            return null;
        } else {
            return (Categorie) query.getResultList().get(0);
        }
    }

    @Override
    public Categorie rechercher(long id) {
        return em.find(Categorie.class,id);
    }

    @Override
    public List<Categorie> rechercherTout() {
       return em.createNamedQuery("Categorie.rechercherTout").getResultList();
    }
}
