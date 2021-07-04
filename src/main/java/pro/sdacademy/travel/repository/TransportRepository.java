package pro.sdacademy.travel.repository;

import pro.sdacademy.travel.entity.Transport;

import javax.persistence.EntityManager;
import java.util.UUID;

public class TransportRepository extends AbstractCRUDRepository<UUID, Transport> {

    public TransportRepository(EntityManager entityManager) {
        super(entityManager, Transport.class);
    }
}
