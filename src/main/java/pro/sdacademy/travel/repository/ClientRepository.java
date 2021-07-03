package pro.sdacademy.travel.repository;

import pro.sdacademy.travel.entity.Client;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class ClientRepository extends AbstractCRUDRepository<Integer, Client> {

    public ClientRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Optional<Client> find(Integer id) {
        return Optional.ofNullable(entityManager.find(Client.class, id));
    }

    @Override
    public List<Client> findAll() {
        return entityManager.createQuery("FROM Client", Client.class).getResultList();
    }
}
