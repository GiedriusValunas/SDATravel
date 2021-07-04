package pro.sdacademy.travel.repository;

import pro.sdacademy.travel.entity.Destination;

import javax.persistence.EntityManager;
import java.util.UUID;

public class DestinationRepository extends AbstractCRUDRepository<UUID, Destination> {

    public DestinationRepository(EntityManager entityManager) {
        super(entityManager, Destination.class);
    }
}
