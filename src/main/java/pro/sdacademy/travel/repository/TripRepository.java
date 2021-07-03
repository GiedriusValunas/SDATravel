package pro.sdacademy.travel.repository;

import pro.sdacademy.travel.entity.Trip;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class TripRepository implements CRUDRepository<Integer, Trip> {

    private final EntityManager entityManager;

    public TripRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void create(Trip trip) {
        entityManager.persist(trip);
    }

    @Override
    public Optional<Trip> find(Integer id) {
        return Optional.ofNullable(entityManager.find(Trip.class, id));
    }

    @Override
    public List<Trip> findAll() {
        return entityManager.createQuery("FROM Trip", Trip.class).getResultList();
    }

    @Override
    public void update(Trip trip) {
        entityManager.persist(trip);
    }

    @Override
    public void delete(Trip entity) {
        entityManager.remove(entity);
    }
}
