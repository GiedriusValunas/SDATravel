package pro.sdacademy.travel.test;

import pro.sdacademy.travel.SDATravelException;
import pro.sdacademy.travel.entity.Client;
import pro.sdacademy.travel.repository.ClientRepository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ClientTestCase extends BaseTestCase<Client, ClientRepository> {

    public ClientTestCase(ClientRepository repository) {
        super(repository);
    }

    @Override
    public Client testCreate() {
        Client client = new Client();
        client.setName("John");
        client.setSurname("Smith");
        client.setBirthdate(LocalDate.now().minus(18, ChronoUnit.YEARS));
        repository.save(client);
        return client;
    }

    @Override
    public void testUpdate(Client client) {
        client.setName("Marry");
        client.setSurname("Ann");
        client.setBirthdate(LocalDate.now().minus(24, ChronoUnit.YEARS));
        repository.save(client);
    }
}
