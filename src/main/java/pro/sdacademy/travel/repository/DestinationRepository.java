package pro.sdacademy.travel.repository;

import pro.sdacademy.travel.entity.Destination;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class DestinationRepository extends AbstractCRUDRepository<UUID, Destination> {

    public DestinationRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Optional<Destination> find(UUID id) {
        return Optional.ofNullable(entityManager.find(Destination.class, id));
    }

    @Override
    public List<Destination> findAll() {
        return entityManager.createQuery("FROM Destination", Destination.class).getResultList();
    }
}
