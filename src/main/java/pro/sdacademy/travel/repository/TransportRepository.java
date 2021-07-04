package pro.sdacademy.travel.repository;

import pro.sdacademy.travel.entity.Transport;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.UUID;

public class TransportRepository extends AbstractCRUDRepository<UUID, Transport> {

    public TransportRepository(EntityManager entityManager) {
        super(entityManager, Transport.class);
    }

    public List<Transport> findTransportByDestinationName(String destinationName) {
        return entityManager.createQuery("" +
                        "SELECT t " +
                        "FROM Transport t " +
                        "JOIN t.destinations d " +
                        "WHERE d.name = ?1",
                Transport.class)
                .setParameter(1, destinationName)
                .getResultList();
    }
}
