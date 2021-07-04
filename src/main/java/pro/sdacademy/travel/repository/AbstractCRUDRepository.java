package pro.sdacademy.travel.repository;

import pro.sdacademy.travel.entity.DbEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.Optional;

public abstract class AbstractCRUDRepository<ID, T extends DbEntity<ID>> implements CRUDRepository<ID, T> {

    protected final EntityManager entityManager;
    private Class<T> entityClass;

    protected AbstractCRUDRepository(EntityManager entityManager, Class<T> entityClass) {
        this.entityManager = entityManager;
        this.entityClass = entityClass;
    }

    @Override
    public void save(T entity) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(entity);
        transaction.commit();
    }

    @Override
    public Optional<T> find(ID id) {
        return Optional.ofNullable(entityManager.find(entityClass, id));
    }

    @Override
    public List<T> findAll() {
        return entityManager.createQuery("FROM " + entityClass.getSimpleName(), entityClass).getResultList();
    }

    @Override
    public void delete(T entity) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.remove(entity);
        transaction.commit();
    }

    private void runInTransaction(Runnable action) {
        // Get transaction from pool
        EntityTransaction transaction = entityManager.getTransaction();
        // Start transaction
        transaction.begin();

        // Run provided database action
        action.run();

        // Commit transaction to database
        transaction.commit();
    }
}
