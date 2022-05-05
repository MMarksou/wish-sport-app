package univ.tln.i243.groupe1.daos;
import univ.tln.i243.groupe1.entitees.Enregistrement;

import javax.persistence.EntityManager;
import java.util.List;

public class EnregistrementDao extends DAO<Enregistrement>{

    public static EnregistrementDao of(EntityManager entityManager){
        return new EnregistrementDao(entityManager);
    }

    public EnregistrementDao(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public List<Enregistrement> findAll() {
        return entityManager.createNamedQuery("enregistrement.findAll",Enregistrement.class).getResultList();
    }

    public List<Enregistrement> findByCategorie(String cat) {
        return entityManager.createNamedQuery("enregistrement.findByCategorie",Enregistrement.class).setParameter("cat",cat).getResultList();
    }
}
