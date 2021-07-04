package pro.sdacademy.travel.repository;

import pro.sdacademy.travel.entity.TripOrder;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class TripOrderRepository extends AbstractCRUDRepository<Integer, TripOrder> {

    public TripOrderRepository(EntityManager entityManager) {
        super(entityManager, TripOrder.class);
    }
}
