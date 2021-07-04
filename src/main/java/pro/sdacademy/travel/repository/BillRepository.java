package pro.sdacademy.travel.repository;

import pro.sdacademy.travel.entity.Bill;

import javax.persistence.EntityManager;
import java.util.List;

public class BillRepository extends AbstractCRUDRepository<Integer, Bill> {

    public BillRepository(EntityManager entityManager) {
        super(entityManager, Bill.class);
    }

    public List<Bill> findBillsByClientId(Integer clientId) {
        return entityManager.createQuery("FROM Bill b WHERE b.client.id = ?1", Bill.class)
                .setParameter(1, clientId)
                .getResultList();
    }

    public Double getTotalUnpaidAmountByClientId(Integer clientId) {
        return entityManager.createQuery("" +
                        "SELECT SUM(b.trip.price) " +
                        "FROM Bill b " +
//                        "JOIN b.trip " +
                        "WHERE b.client.id = ?1 AND b.cleared IS NULL",
                Double.class)
                .setParameter(1, clientId)
                .getSingleResult();
    }
}
