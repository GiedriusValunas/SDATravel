package pro.sdacademy.travel.test;

import pro.sdacademy.travel.entity.Trip;
import pro.sdacademy.travel.repository.TripRepository;

public class TripTestCase extends BaseTestCase<Trip, TripRepository> {

    public TripTestCase(TripRepository repository) {
        super(repository);
    }

    @Override
    public Trip testCreate() {
        Trip trip = new Trip();
        trip.setDestination("Tokyo");
        trip.setPrice(999.99);
        repository.save(trip);
        return trip;
    }

    @Override
    public void testUpdate(Trip trip) {
        trip.setDestination("Turkey");
        trip.setPrice(299.49);
        repository.save(trip);
    }

    @Override
    public void testDelete(Trip trip) {
        repository.delete(trip);
    }
}
