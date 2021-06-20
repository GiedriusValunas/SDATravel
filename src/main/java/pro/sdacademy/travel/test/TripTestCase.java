package pro.sdacademy.travel.test;

import pro.sdacademy.travel.SDATravelException;
import pro.sdacademy.travel.entity.Trip;
import pro.sdacademy.travel.repository.TripRepository;

public class TripTestCase extends BaseTestCase<Trip, TripRepository> {

    public TripTestCase(TripRepository repository) {
        super(repository);
    }

    @Override
    public void testCreate() {
        Trip trip = new Trip();
        trip.setDestination("Tokyo");
        trip.setPrice(999.99);
        repository.create(trip);
    }

    @Override
    public void testUpdate() {
        Trip t = repository.findAll().stream()
                .skip(1)
                .findFirst()
                .orElseThrow(SDATravelException::new);

        t.setDestination("Turkey");
        t.setPrice(299.49);
        repository.save(t);
    }

    @Override
    public void testDelete() {
        Trip t = repository.findAll().stream()
                .skip(2)
                .findFirst()
                .orElseThrow(SDATravelException::new);
        repository.delete(t);
    }
}
