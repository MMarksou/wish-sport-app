package univ.tln.i243.groupe1.daos;

import univ.tln.i243.groupe1.entitees.Categorie;

import javax.management.Query;
import javax.persistence.EntityManager;
import java.util.List;

public class CategorieDao extends DAO<Categorie>{

    public CategorieDao(EntityManager em) {
        super(em);
    }


    @Override
    public Categorie find(long id) {
        return em.find(Categorie.class,id);
    }

    @Override
    public List<Categorie> findAll() {
        Query query = em.createNamedQuery("")

        return null;
    }
}
