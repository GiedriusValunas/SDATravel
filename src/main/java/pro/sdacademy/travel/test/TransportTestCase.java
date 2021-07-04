package pro.sdacademy.travel.test;

import pro.sdacademy.travel.entity.Transport;
import pro.sdacademy.travel.repository.DestinationRepository;
import pro.sdacademy.travel.repository.TransportRepository;

import java.util.Arrays;
import java.util.stream.Collectors;

import static pro.sdacademy.travel.entity.Transport.TransportType.BUS;

public class TransportTestCase extends BaseTestCase<Transport, TransportRepository> {

    private final DestinationRepository destinationRepository;

    public TransportTestCase(TransportRepository repository, DestinationRepository destinationRepository) {
        super(repository);
        this.destinationRepository = destinationRepository;
    }

    @Override
    public Transport testCreate() {
        return testCreate("LT", "UK");
    }

    private Transport testCreate(String... destinationName) {
        Transport transport = new Transport();
        transport.setNumber("ABC123");
        transport.setType(BUS);
        transport.setDestinations(Arrays.stream(destinationName)
                .map(destinationRepository::getOrCreateDestinationByName)
                .collect(Collectors.toSet()));
        repository.save(transport);
        return transport;
    }

    @Override
    public void testUpdate(Transport transport) {
        transport.setNumber("AAA111");
        repository.save(transport);
    }

    public void extraTestFindTransportByDestinationName() {
        testCreate("JP");
        testCreate("JP");
        testCreate("JP");
        repository.findTransportByDestinationName("JP").forEach(System.out::println);
    }
}
