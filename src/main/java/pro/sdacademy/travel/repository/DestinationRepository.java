package pro.sdacademy.travel.repository;

import pro.sdacademy.travel.entity.Destination;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.Optional;
import java.util.UUID;

public class DestinationRepository extends AbstractCRUDRepository<UUID, Destination> {

    public DestinationRepository(EntityManager entityManager) {
        super(entityManager, Destination.class);
    }

    public Destination getOrCreateDestinationByName(String destinationName) {
        return findDestinationByName(destinationName).orElseGet(() -> {
            Destination destination = new Destination(destinationName);
            save(destination);
            return destination;
        });
    }

    public Optional<Destination> findDestinationByName(String destinationName) {
        try {
            return Optional.of(entityManager.createQuery("FROM Destination d WHERE d.name = ?1", Destination.class)
                    .setParameter(1, destinationName)
                    .getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}
