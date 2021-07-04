package pro.sdacademy.travel.repository;

import pro.sdacademy.travel.entity.Client;

import javax.persistence.EntityManager;

public class ClientRepository extends AbstractCRUDRepository<Integer, Client> {

    public ClientRepository(EntityManager entityManager) {
        super(entityManager, Client.class);
    }
}
