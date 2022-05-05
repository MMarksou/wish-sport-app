package univ.tln.i243.groupe1.daos;

import lombok.AllArgsConstructor;
import univ.tln.i243.groupe1.entitees.Entite;

import javax.persistence.EntityManager;
import java.lang.reflect.ParameterizedType;
import java.util.List;

@AllArgsConstructor
public abstract class DAO<E extends Entite> {

    protected EntityManager em;

    public E persist(E t){
        em.getTransaction().begin();
        em.persist(t);
        em.getTransaction().commit();

        em.refresh(t);
        return t;
    }

    public abstract E find(long id);

    public abstract List<E> findAll();

    public E merge(E t){
        em.getTransaction().begin();
        em.merge(t);
        em.getTransaction().commit();

        return t;
    }

    public void delete(E t){
        em.getTransaction().begin();
        em.remove(t);
        em.getTransaction().commit();
    }

    public void refresh(E t){em.refresh(t);}

    public void clear(){em.clear();}

    public void close(){em.close();}

    public void flush(){em.flush();}

}