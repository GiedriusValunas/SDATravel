package pro.sdacademy.travel.repository;

import pro.sdacademy.travel.entity.TripOrder;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class TripOrderRepository extends AbstractCRUDRepository<Integer, TripOrder> {

    public TripOrderRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Optional<TripOrder> find(Integer id) {
        return Optional.ofNullable(entityManager.find(TripOrder.class, id));
    }

    @Override
    public List<TripOrder> findAll() {
        return entityManager.createQuery("FROM TripOrder", TripOrder.class).getResultList();
    }
}
