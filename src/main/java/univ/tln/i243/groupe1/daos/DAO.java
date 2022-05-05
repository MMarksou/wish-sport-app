package univ.tln.i243.groupe1.daos;

import lombok.AllArgsConstructor;

import javax.persistence.EntityManager;
import java.lang.reflect.ParameterizedType;
import java.util.List;

@AllArgsConstructor
public abstract class DAO<E> {
    @SuppressWarnings("unchecked")
    protected final Class<E> entityClass = (Class<E>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    protected EntityManager entityManager;

    public void persist(E entity) {

        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
        System.out.println("ok");
    }

    public void remove(long id) {
        entityManager.getTransaction().begin();
        entityManager.remove(findById(id));
        entityManager.getTransaction().commit();
    }

    public void remove(E entity) {
        entityManager.getTransaction().begin();
        entityManager.remove(entity);
        entityManager.getTransaction().commit();
    }

    public E findById(Object id) {
        return entityManager.find(entityClass, id);
    }

    abstract List<E> findAll();

}