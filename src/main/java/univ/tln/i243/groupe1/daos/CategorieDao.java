package univ.tln.i243.groupe1.daos;

import univ.tln.i243.groupe1.entitees.Categorie;

import javax.persistence.EntityManager;
import java.util.List;

public class CategorieDao extends DAO<Categorie>{

    public static CategorieDao of(EntityManager entityManager){
        return new CategorieDao(entityManager);
    }

    public CategorieDao(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public List<Categorie> findAll() {
        return entityManager.createNamedQuery("categorie.findAll",Categorie.class).getResultList();
    }
}
