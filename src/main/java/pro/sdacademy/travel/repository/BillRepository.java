package pro.sdacademy.travel.repository;

import pro.sdacademy.travel.entity.Bill;

import javax.persistence.EntityManager;

public class BillRepository extends AbstractCRUDRepository<Integer, Bill> {

    public BillRepository(EntityManager entityManager) {
        super(entityManager, Bill.class);
    }
}
