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
    public Categorie findByNom(String nom){
        Query query = em.createNamedQuery("Categorie.findByNom");
        query.setParameter("nom",nom);
        Categorie categorie = (Categorie) query.getResultList().get(0);
        return categorie;
    }

    @Override
    public Categorie find(long id) {
        return em.find(Categorie.class,id);
    }

    @Override
    public List<Categorie> findAll() {
        Query query = em.createNamedQuery("Categorie.findALl");
        List<Categorie> categories = query.getResultList();
        return categories;
    }
}
