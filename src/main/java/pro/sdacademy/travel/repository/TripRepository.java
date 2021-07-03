package pro.sdacademy.travel.repository;

import pro.sdacademy.travel.entity.Trip;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class TripRepository extends AbstractCRUDRepository<Integer, Trip> {

    public TripRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Optional<Trip> find(Integer id) {
        return Optional.ofNullable(entityManager.find(Trip.class, id));
    }

    @Override
    public List<Trip> findAll() {
        return entityManager.createQuery("FROM Trip", Trip.class).getResultList();
    }
}
