package pro.sdacademy.travel.test;

import pro.sdacademy.travel.entity.Destination;
import pro.sdacademy.travel.entity.Transport;
import pro.sdacademy.travel.repository.TransportRepository;

import java.util.Arrays;
import java.util.HashSet;

import static pro.sdacademy.travel.entity.Transport.TransportType.BUS;

public class TransportTestCase extends BaseTestCase<Transport, TransportRepository> {

    public TransportTestCase(TransportRepository repository) {
        super(repository);
    }

    @Override
    public Transport testCreate() {
        Transport transport = new Transport();
        transport.setNumber("ABC123");
        transport.setType(BUS);
        transport.setDestinations(new HashSet<>(Arrays.asList(
                new Destination("LT"),
                new Destination("UK"))));
        repository.save(transport);
        return transport;
    }

    @Override
    public void testUpdate(Transport transport) {
        transport.setNumber("AAA111");
        repository.save(transport);
    }
}
