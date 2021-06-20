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
        repository.create(client);
        return repository.findAll().stream().findFirst().orElseThrow(SDATravelException::new);
    }

    @Override
    public void testUpdate() {
        Client c = repository.findAll().stream()
                .skip(1)
                .findFirst()
                .orElseThrow(SDATravelException::new);
        c.setName("Marry");
        c.setSurname("Ann");
        c.setBirthdate(LocalDate.now().minus(24, ChronoUnit.YEARS));
        repository.save(c);
    }

    @Override
    public void testDelete() {
        Client c = repository.findAll().stream()
                .skip(2)
                .findFirst()
                .orElseThrow(SDATravelException::new);
        repository.delete(c);
    }
}
