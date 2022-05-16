package univ.tln.i243.groupe1.daos;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import univ.tln.i243.groupe1.entitees.Entitee;

import javax.persistence.EntityManager;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

@AllArgsConstructor
@Log
public abstract class DAO<E extends Entitee> {

    protected EntityManager em;

    public void persister(E t) {
        try {
            em.getTransaction().begin();
            em.persist(t);
            em.getTransaction().commit();
            em.refresh(t);
        } catch (Exception e){
            log.info("ok " + e);
        }
    }

    public abstract E rechercher(long id);

    public abstract E rechercherParNom(String nom);

    public abstract List<E> rechercherTout();

    public E fusionner(E t){
        em.getTransaction().begin();
        em.merge(t);
        em.getTransaction().commit();
        return t;
    }

    public void supprimer(E t){
        em.getTransaction().begin();
        em.remove(t);
        em.getTransaction().commit();
    }

    public void recharger(E t){em.refresh(t);}

    public void nettoyer(){em.clear();}

    public void fermer(){em.close();}

    public void flush(){em.flush();}

}