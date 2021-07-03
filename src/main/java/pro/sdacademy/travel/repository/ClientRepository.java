package pro.sdacademy.travel.repository;

import pro.sdacademy.travel.entity.Client;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class ClientRepository implements CRUDRepository<Integer, Client> {

    private final EntityManager entityManager;

    public ClientRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void create(Client client) {
        entityManager.persist(client);
    }

    @Override
    public Optional<Client> find(Integer id) {
        return Optional.ofNullable(entityManager.find(Client.class, id));
    }

    @Override
    public List<Client> findAll() {
        return entityManager.createQuery("from Client", Client.class).getResultList();
    }

    @Override
    public void update(Client client) {
        entityManager.persist(client);
    }

    @Override
    public void delete(Client client) {
        entityManager.remove(client);
    }
}
