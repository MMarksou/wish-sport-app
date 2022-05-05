package univ.tln.i243.groupe1.daos;
import univ.tln.i243.groupe1.entitees.Enregistrement;

import javax.persistence.EntityManager;
import java.util.List;

public class EnregistrementDao extends DAO<Enregistrement>{

    public EnregistrementDao(EntityManager entityManager) {
        super(entityManager);
    }


    @Override
    public Enregistrement find(long id) {
        return null;
    }

    @Override
    public List<Enregistrement> findAll() {
        return null;
    }
}
